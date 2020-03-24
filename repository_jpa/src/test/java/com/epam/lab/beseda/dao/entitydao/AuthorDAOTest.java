package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.AuthorDAOInterface;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.entity.News;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.util.DatabaseConfigure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static com.epam.lab.beseda.util.LoggerName.TRACE_LOGGER;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class})
public class AuthorDAOTest {

    private Logger log= LogManager.getLogger(TRACE_LOGGER);

    @Autowired
    private DatabaseConfigure dbc;

    @Autowired
    private AuthorDAOInterface authorDAO;

    @Before
    public void fillDatabase() {
        dbc.fillDatabase();
    }

    @After
    public void cleanDatabase() {
        dbc.cleanDatabase();
    }


    @Test
    public void testAdd_01() throws DAOLayerException {
        Author entity = new Author("Maxim", "Govorishev");
        int id = authorDAO.add(entity);
        Author author = authorDAO.getEntityById(id);
        Assert.assertEquals(entity, author);
    }

    @Test
    public void testAdd_02() throws DAOLayerException {
        Author entity = new Author("Viachaslav", "Morgunov");
        int id01 = authorDAO.add(entity);
        int id02 = authorDAO.add(new Author(entity.getName(), entity.getSurname()));
        Assert.assertEquals(id01, id02);
    }

    @Test
    public void testUpdate() {
        Author entity = new Author("Liza", "Kamenkova");
        int id = authorDAO.add(entity);
        String newName = "MMM";

        Author author = authorDAO.getEntityById(id);
        author.setName(newName);
        authorDAO.update(author);
        Author updatedAuthor = authorDAO.getEntityById(id);
        Assert.assertEquals(newName, updatedAuthor.getName());
    }

    @Test
    public void testGetAuthorByNameSurname() {
        Author entity = new Author("Stanislav", "Krot");
        authorDAO.add(entity);
        Author author = authorDAO.getAuthorByNameSurname(entity.getName(), entity.getSurname());
        Assert.assertEquals(entity, author);
    }

    @Test
    public void testGetEntityById_noSuchEntity() {
        Author author = authorDAO.getEntityById(1000);
        Assert.assertNull(author);
    }

    @Test
    public void testGetExistentEntityById() {
        Author author = authorDAO.getEntityById(2);
        log.debug(author);
        List<News> newsList = author.getNewsList();

        Assert.assertEquals(4, newsList.size());
    }

    @Test
    public void testDelete() {
        Author entity = new Author("Stanislav", "Krot");
        authorDAO.delete(entity.getId());
        Author author = authorDAO.getEntityById(entity.getId());
        Assert.assertNull(author);
    }

    @Test
    public void testGetAll() {
        List<Author> authors = authorDAO.getAll();
        Assert.assertTrue(authors.size() > 0);
    }

}
