package com.epam.lab.beseda.dao.entitydao;


import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.util.DatabaseConfigure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static com.epam.lab.beseda.util.LoggerName.TRACE_LOGGER;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class})
public class TagDAOTest {

    @Autowired
    @Qualifier("tagDao")
    private TagDAOInterface tagDao;

    private Logger log = LogManager.getLogger(TRACE_LOGGER);

    private static Tag entity;

    @Autowired
    private DatabaseConfigure dbc;

    @Before
    public void fillDatabase() {
        dbc.fillDatabase();
    }

    @After
    public void cleanDatabase() {
        dbc.cleanDatabase();
    }

    private void simpleAdd_getEntity() {
        int id = tagDao.add(entity);
        Tag tag = tagDao.getEntityById(id);
        Assert.assertEquals(entity, tag);
    }


    @Test
    public void testAdd_01() {
        Tag tag = new Tag("timer");
        int id01 = tagDao.add(tag);
        int id02 = tagDao.add(new Tag(tag.getName()));
        Assert.assertEquals(id01, id02);
    }

    @Test
    public void testAdd_02() {
        Tag tag = new Tag("nnnnn");
        int id = tagDao.add(tag);
        Tag tag01 = tagDao.getEntityById(id);
        Assert.assertEquals(tag, tag01);
    }

    @Test
    public void testUpdate() {
        Tag tag01 = new Tag("nanny");
        int id = tagDao.add(tag01);
        String newName = "MMM";

        Tag tag = tagDao.getEntityById(id);
        tag.setName(newName);
        tagDao.update(tag);
        Tag updatedRole = tagDao.getEntityById(id);
        Assert.assertEquals(tag, updatedRole);
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        Tag entity = new Tag("new_news");
        int id = tagDao.add(entity);
        Tag tag = tagDao.getEntityById(id);
        Assert.assertEquals(entity, tag);
    }

    @Test
    public void testGetEntityById_noSuchEntity() throws DAOLayerException {
        Tag tag = tagDao.getEntityById(1000);
        Assert.assertNull(tag);
    }

    @Test
    public void testDelete() {
        Tag newTag = new Tag("join");
        int id = tagDao.add(newTag);
        tagDao.delete(id);
        Tag tag = tagDao.getEntityById(id);
        Assert.assertNull(tag);
    }

    @Test
    public void testGetAll() {
        List<Tag> tags = tagDao.getAll();
        Assert.assertTrue(tags.size() > 3);
    }

    @Test
    public void testGetEntityByName() throws DAOLayerException {
        Tag newTag = new Tag("specific");
        tagDao.add(newTag);
        Tag tag = tagDao.getEntityByName(newTag.getName());
        Assert.assertEquals(newTag, tag);
    }

}
