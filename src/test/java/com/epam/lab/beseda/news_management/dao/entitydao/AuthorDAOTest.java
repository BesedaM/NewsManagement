package com.epam.lab.beseda.news_management.dao.entitydao;

import com.epam.lab.beseda.news_management.config.TestConfig;
import com.epam.lab.beseda.news_management.dao.daointeface.AuthorDAOInterface;
import com.epam.lab.beseda.news_management.dao.exception.DAOLayerException;
import com.epam.lab.beseda.news_management.dao.util.DatabaseConfigure;
import com.epam.lab.beseda.news_management.entity.Author;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class AuthorDAOTest {

    @Autowired
    private DatabaseConfigure dbc;

    @Autowired
    private AuthorDAOInterface authorDAO;

    private static Author entity;

    @BeforeClass
    public static void initData() {
        entity = new Author("Maxim", "Govorishev");
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
        int id = authorDAO.add(entity);
        entity.setId(id);
        Author author = authorDAO.getEntityById(id);
        Assert.assertEquals(entity, author);
    }


    @Test
    public void testAdd() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testUpdate() throws DAOLayerException {
        int id = authorDAO.add(entity);
        String newName = "MMM";

        Author author = authorDAO.getEntityById(id);
        author.setName(newName);
        authorDAO.update(author);
        Author updatedAuthor = authorDAO.getEntityById(id);
        Assert.assertEquals(newName, updatedAuthor.getName());
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testDelete() throws DAOLayerException {
        simpleAdd_getEntity();
        authorDAO.delete(entity.getId());
        Author author = authorDAO.getEntityById(entity.getId());
        Assert.assertNull(author);
    }

    @Test
    public void testGetAll() {
        List<Author> authors = authorDAO.getAll();
        Assert.assertTrue(authors.size() > 0);
    }

    @Test
    public void testGetNewsId(){
        List<Integer> newsId=authorDAO.getNewsId(3);
        Assert.assertEquals(3,newsId.size());
    }

}
