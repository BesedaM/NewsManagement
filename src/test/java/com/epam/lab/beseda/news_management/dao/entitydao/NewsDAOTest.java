package com.epam.lab.beseda.news_management.dao.entitydao;

import com.epam.lab.beseda.news_management.config.TestConfig;
import com.epam.lab.beseda.news_management.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.news_management.dao.exception.DAOLayerException;
import com.epam.lab.beseda.news_management.dao.util.DatabaseConfigure;
import com.epam.lab.beseda.news_management.entity.EnumEntity;
import com.epam.lab.beseda.news_management.entity.News;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class NewsDAOTest {

    @Autowired
    private DatabaseConfigure dbc;

    private static News entity;

    @Autowired
    private NewsDAOInterface newsDao;

    @BeforeClass
    public static void initData() {
        entity = new News("NEW", "1111", "123456789");
        LocalDate now = LocalDate.now();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.YEAR, now.getYear());
        currentDate.set(Calendar.MONTH, now.getMonthValue());
        currentDate.set(Calendar.DAY_OF_MONTH, now.getDayOfMonth());
        currentDate.set(Calendar.HOUR, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        entity.setCreationDate(currentDate);
        entity.setModificationDate(currentDate);
    }

    @Before
    public void fillDatabase() {
        dbc.fillDatabase();
    }

    @After
    public void cleanDatabase() {
        dbc.cleanDatabase();
    }

    private void simpleAdd_getEntity() throws DAOLayerException {
        int id = newsDao.add(entity);
        entity.setId(id);
        News news = newsDao.getEntityById(id);
        Assert.assertEquals(entity.getTitle(), news.getTitle());
    }

    @Test
    public void testAdd() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testUpdate() throws DAOLayerException {
        int id = newsDao.add(entity);
        String shortText = "MMM";

        News news = newsDao.getEntityById(id);
        news.setShortText(shortText);
        newsDao.update(news);
        News updatedNews = newsDao.getEntityById(news.getId());
        Assert.assertEquals(shortText, updatedNews.getShortText());
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testDelete() throws DAOLayerException {
        simpleAdd_getEntity();
        newsDao.delete(entity.getId());
        News news = newsDao.getEntityById(entity.getId());
        Assert.assertNull(news);
    }

    @Test
    public void testGetAll() {
        List<News> newsList = newsDao.getAll();
        Assert.assertTrue(newsList.size() > 0);
    }

    @Test
    public void testAddNewsTag() throws DAOLayerException {
        simpleAdd_getEntity();
        int id = entity.getId();
        newsDao.addNewsTag(id, 1);
        List<EnumEntity> tagList = newsDao.getNewsTags(id);
        Assert.assertTrue(tagList.size() == 1 && tagList.get(0).getId() == 1);
    }

    @Test
    public void testDeleteNewsTag() throws DAOLayerException {
        simpleAdd_getEntity();
        int id = entity.getId();
        newsDao.addNewsTag(id, 1);
        newsDao.addNewsTag(id, 2);
        newsDao.deleteNewsTag(id, 1);
        List<EnumEntity> tagList = newsDao.getNewsTags(id);
        Assert.assertTrue(tagList.size() == 1 && tagList.get(0).getId() == 2);
    }

    @Test
    public void testGetNewsTagsNames() throws DAOLayerException {
        simpleAdd_getEntity();
        int id = entity.getId();
        newsDao.addNewsTag(id, 1);
        newsDao.addNewsTag(id, 2);
        List<String> newsTagsList = newsDao.getNewsTagsNames(id);
        Assert.assertTrue(newsTagsList.contains("advice") && newsTagsList.contains("granny"));
    }

    @Test
    public void testAddAuthorId() throws DAOLayerException {
        simpleAdd_getEntity();
        int authorId = 1;
        int id = entity.getId();
        newsDao.addAuthor(id, authorId);
        int daoId = newsDao.getAuthorId(id);
        Assert.assertEquals(authorId, daoId);
    }

    @Test
    public void testGetNewsId() {
        int tagId = 1;
        List<Integer> newsIdList = newsDao.getNewsId(tagId);
        Assert.assertTrue(newsIdList.size() > 0);
    }

    @Test
    public void testGetNewsNumber() {
        int newsNumber = newsDao.getNewsNumber();
        Assert.assertEquals(13,newsNumber);
    }

}
