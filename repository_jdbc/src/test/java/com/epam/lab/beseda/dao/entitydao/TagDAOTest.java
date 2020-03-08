package com.epam.lab.beseda.dao.entitydao;


import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.util.DatabaseConfigure;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class})
public class TagDAOTest {

    @Autowired
    @Qualifier("tagDao")
    private TagDAOInterface tagDao;

    private static Tag entity;

    @Autowired
    private DatabaseConfigure dbc;

    @BeforeClass
    public static void initData() {
        entity = new Tag("new_news");
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
        int id = tagDao.add(entity);
        Tag tag = tagDao.getEntityById(id);
        Assert.assertEquals(entity, tag);
    }


    @Test
    public void testAdd_01() throws DAOLayerException {
        int id01 = tagDao.add(entity);
        int id02 = tagDao.add(new Tag(entity.getName()));
        Assert.assertEquals(id01, id02);
    }

    @Test
    public void testAdd_02() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testUpdate() throws DAOLayerException {
        int id = tagDao.add(entity);
        String newName = "MMM";

        Tag tag = tagDao.getEntityById(id);
        tag.setName(newName);
        tagDao.update(tag);
        Tag updatedRole = tagDao.getEntityById(id);
        Assert.assertEquals(tag, updatedRole);
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testGetEntityById_noSuchEntity() throws DAOLayerException {
        Tag tag = tagDao.getEntityById(1000);
        Assert.assertNull(tag);
    }

    @Test
    public void testDelete() throws DAOLayerException {
        simpleAdd_getEntity();
        tagDao.delete(entity.getId());
        Tag tag = tagDao.getEntityById(entity.getId());
        Assert.assertNull(tag);
    }

    @Test
    public void testGetAll() {
        List<Tag> tags = tagDao.getAll();
        System.out.println(Arrays.toString(tags.toArray()));
        Assert.assertTrue(tags.size() > 3);
    }

    @Test
    public void testGetEntityByName() throws DAOLayerException {
        simpleAdd_getEntity();
        Tag tag = tagDao.getEntityByName(entity.getName());
        Assert.assertEquals(entity, tag);
    }

}
