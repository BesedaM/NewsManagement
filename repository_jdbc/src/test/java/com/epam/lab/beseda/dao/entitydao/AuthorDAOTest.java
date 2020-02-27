package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.AuthorDAOInterface;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.util.DatabaseConfigure;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class})
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
        Author author = authorDAO.getEntityById(id);
        Assert.assertEquals(entity, author);
    }


    @Test
    public void testAdd_01() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testAdd_02() throws DAOLayerException {
        int id01 = authorDAO.add(entity);
        int id02 = authorDAO.add(new Author(entity.getName(),entity.getSurname()));
        Assert.assertEquals(id01, id02);
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
    public void testGetAuthorByNameSurname() throws DAOLayerException {
        authorDAO.add(entity);
        Author author = authorDAO.getAuthorByNameSurname(entity.getName(), entity.getSurname());
        Assert.assertEquals(entity, author);
    }

    @Test
    public void testGetEntityById_noSuchEntity() throws DAOLayerException {
        Author author = authorDAO.getEntityById(1000);
        Assert.assertNull(author);
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
    public void testGetNewsId() {
        List<Integer> newsId = authorDAO.getNewsId(3);
        Assert.assertEquals(3, newsId.size());
    }


}
