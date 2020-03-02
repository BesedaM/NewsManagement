package com.epam.lab.beseda.dao.entitydao;


import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.EnumEntityDAOInterface;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.util.DatabaseConfigure;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfiguration.class})
public class TagDAOTest {

    @Autowired
    @Qualifier("tagDao")
    private EnumEntityDAOInterface tagDao;

    private static EnumEntity entity;

    @Autowired
    private DatabaseConfigure dbc;

    @BeforeClass
    public static void initData() {
        entity = new EnumEntity("new_news");
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
        EnumEntity tag = tagDao.getEntityById(id);
        Assert.assertEquals(entity, tag);
    }


    @Test
    public void testAdd_01() throws DAOLayerException {
        int id01 = tagDao.add(entity);
        int id02 = tagDao.add(new EnumEntity(entity.getName()));
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

        EnumEntity tag = tagDao.getEntityById(id);
        tag.setName(newName);
        tagDao.update(tag);
        EnumEntity updatedRole = tagDao.getEntityById(id);
        Assert.assertEquals(tag, updatedRole);
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testGetEntityById_noSuchEntity() throws DAOLayerException {
        EnumEntity tag = tagDao.getEntityById(1000);
        Assert.assertNull(tag);
    }

    @Test
    public void testDelete() throws DAOLayerException {
        simpleAdd_getEntity();
        tagDao.delete(entity.getId());
        EnumEntity tag = tagDao.getEntityById(entity.getId());
        Assert.assertNull(tag);
    }

    @Test
    public void testGetAll() {
        List<EnumEntity> tags = tagDao.getAll();
        Assert.assertTrue(tags.size() > 3);
    }

    @Test
    public void testGetEntityByName() throws DAOLayerException {
        simpleAdd_getEntity();
        EnumEntity tag = tagDao.getEntityByName(entity.getName());
        Assert.assertEquals(entity, tag);
    }

}
