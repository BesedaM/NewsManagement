package com.epam.lab.beseda.news_management.dao.entitydao;


import com.epam.lab.beseda.news_management.config.TestConfig;
import com.epam.lab.beseda.news_management.dao.daointeface.EnumEntityDAOInterface;
import com.epam.lab.beseda.news_management.dao.exception.DAOLayerException;
import com.epam.lab.beseda.news_management.dao.util.DatabaseConfigure;
import com.epam.lab.beseda.news_management.entity.EnumEntity;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class TagDAOTest {

    @Autowired
    @Qualifier("tagDAO")
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
        entity.setId(id);
        EnumEntity tag = tagDao.getEntityById(id);
        Assert.assertEquals(entity, tag);
    }


    @Test
    public void testAdd() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testUpdate() throws DAOLayerException {
        int id = tagDao.add(entity);
        String newName = "MMM";

        EnumEntity tag = tagDao.getEntityById(id);
        tag.setValue(newName);
        tagDao.update(tag);
        EnumEntity updatedRole = tagDao.getEntityById(id);
        Assert.assertEquals(tag, updatedRole);
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        simpleAdd_getEntity();
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
        EnumEntity tag = tagDao.getEntityByName(entity.getValue());
        Assert.assertEquals(entity, tag);
    }

}
