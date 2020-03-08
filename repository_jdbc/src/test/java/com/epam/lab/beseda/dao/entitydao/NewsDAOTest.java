package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.util.DatabaseConfigure;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class})
public class NewsDAOTest {

    @Autowired
    private DatabaseConfigure dbc;

    private static News entity;

    @Autowired
    private NewsDAOInterface newsDao;

    @BeforeClass
    public static void initData() {
        entity = new News("NEW", "1111", "123456789");
        LocalDate now = LocalDate.of(2000, 10, 10);
        entity.setCreationDate(now);
        entity.setModificationDate(now);
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
    public void testGetEntityById_noSuchEntity() throws DAOLayerException {
        News news = newsDao.getEntityById(1000);
        Assert.assertNull(news);
    }


    @Test
    public void testGetNewsByAuthor() {
        Author author = new Author("Alina", "Bodoeva");
        List<News> newsList = newsDao.getNewsByAuthor(author);
        Assert.assertEquals(4, newsList.size());
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
        List<Tag> tagList = newsDao.getNewsTags(id);
        Assert.assertTrue(tagList.size() == 1 && tagList.get(0).getId() == 1);
    }

    @Test
    public void testDeleteNewsTag() throws DAOLayerException {
        simpleAdd_getEntity();
        int id = entity.getId();
        newsDao.addNewsTag(id, 1);
        newsDao.addNewsTag(id, 2);
        newsDao.deleteNewsTag(id, 1);
        List<Tag> tagList = newsDao.getNewsTags(id);
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
        Assert.assertEquals(13, newsNumber);
    }

}
