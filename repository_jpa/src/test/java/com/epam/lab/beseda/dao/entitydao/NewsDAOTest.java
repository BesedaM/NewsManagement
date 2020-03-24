package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.NewsDAOInterface;
import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class})
public class NewsDAOTest {

    @Autowired
    private DatabaseConfigure dbc;

    @Autowired
    private NewsDAOInterface newsDao;

    @Autowired
    private TagDAOInterface tagDAO;

    @Before
    public void fillDatabase() {
        dbc.fillDatabase();
    }

    @After
    public void cleanDatabase() {
        dbc.cleanDatabase();
    }

    @Test
    public void testAdd() {
        News entity = new News("NEW", "1111", "123456789");
        entity.setAuthor(new Author("Dzianis", "Stipanka"));
        entity.setModification_date(LocalDate.now());
        int id = newsDao.add(entity);
        entity.setId(id);
        News news = newsDao.getEntityById(id);
        Assert.assertEquals(entity.getTitle(), news.getTitle());
    }

    @Test
    public void testUpdate() {
        News entity = new News("NEW", "1111", "123456789");
        entity.setAuthor(new Author("Dzianis", "Stipanka"));
        entity.setModification_date(LocalDate.now());
        int id = newsDao.add(entity);
        String shortText = "MMM";

        News news = newsDao.getEntityById(id);
        news.setShort_text(shortText);
        newsDao.update(news);
        News updatedNews = newsDao.getEntityById(news.getId());
        Assert.assertEquals(shortText, updatedNews.getShort_text());
    }


    @Test
    public void testGetEntityById_noSuchEntity() {
        News news = newsDao.getEntityById(1000);
        Assert.assertNull(news);
    }

    @Test
    public void testDelete() {
        News entity = new News("NEW", "1111", "123456789");
        entity.setAuthor(new Author("Dzianis", "Stipanka"));
        entity.setModification_date(LocalDate.now());
        int id = newsDao.add(entity);
        newsDao.delete(id);
        News news = newsDao.getEntityById(entity.getId());
        Assert.assertNull(news);
    }

    @Test
    public void testGetAll() {
        List<News> newsList = newsDao.getAll();
        Assert.assertTrue(newsList.size() > 0);
    }

    @Test
    public void testAddNewsTag() {
        News entity = new News("NEW", "1111", "123456789");
        entity.setAuthor(new Author("Dzianis", "Stipanka"));
        entity.setModification_date(LocalDate.now());
        int id = newsDao.add(entity);
        Tag tag = new Tag("nameee");
        newsDao.addNewsTag(entity, tag);
        News news = newsDao.getEntityById(id);
        Assert.assertEquals(1, news.getTags().size());
    }

    @Test
    public void testDeleteNewsTag() {
        News entity = new News("NEW", "1111", "123456789");
        entity.setAuthor(new Author("Dzianis", "Stipanka"));
        entity.setModification_date(LocalDate.now());
        int id = newsDao.add(entity);
        newsDao.addNewsTag(entity, new Tag("day"));
        newsDao.addNewsTag(entity, new Tag("light"));

        int tagId = tagDAO.getEntityByName("day").getId();

        newsDao.deleteNewsTag(entity.getId(), tagId);
        News savedNews = newsDao.getEntityById(id);
        Assert.assertEquals(1, savedNews.getTags().size());
    }

    @Test
    public void testGetNewsTagsNames() {
        News entity = new News("NEW", "1111", "123456789");
        entity.setAuthor(new Author("Dzianis", "Stipanka"));
        entity.setModification_date(LocalDate.now());
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("granny"));
        tags.add(new Tag("advice"));
        entity.setTags(tags);
        int id = newsDao.add(entity);
        News news = newsDao.getEntityById(id);
        Set<Tag> tagSet = news.getTags();
        Assert.assertTrue(tagSet.contains(new Tag("granny")) && tagSet.contains(new Tag("advice")));
    }

    @Test
    public void testGetNewsByAuthor() {
        List<News> newsList = newsDao.getNewsByAuthor(new Author("Alina", "Bodoeva"));
        Assert.assertEquals(4, newsList.size());
    }

    @Test
    public void testGetNewsByAuthor_noSuchAuthor() {
        List<News> newsList = newsDao.getNewsByAuthor(new Author("Anna", "Bond"));
        Assert.assertEquals(0, newsList.size());
    }

    @Test
    public void findNewsByTagsList(){
        List<String> tagsNames=new ArrayList<>();
        tagsNames.add("granny");
        tagsNames.add("advice");
        List<News> newsList=newsDao.findByTagsList(tagsNames);
        Assert.assertEquals(3,newsList.size());
    }

    @Test
    public void testGetNewsNumber() {
        int newsNumber = newsDao.getNewsNumber();
        Assert.assertEquals(13, newsNumber);
    }

}
