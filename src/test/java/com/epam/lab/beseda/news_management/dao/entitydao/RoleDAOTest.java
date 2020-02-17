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
public class RoleDAOTest {

    @Autowired
    @Qualifier("roleDAO")
    private EnumEntityDAOInterface roleDao;

    private static EnumEntity entity;

    @Autowired
    private DatabaseConfigure dbc;

    @BeforeClass
    public static void initData() {
        entity = new EnumEntity("driver");
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
        int id = roleDao.add(entity);
        entity.setId(id);
        EnumEntity role = roleDao.getEntityById(id);
        Assert.assertEquals(entity, role);
    }


    @Test
    public void testAdd() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testUpdate() throws DAOLayerException {
        int id = roleDao.add(entity);
        String newName = "MMM";

        EnumEntity role = roleDao.getEntityById(id);
        role.setValue(newName);
        roleDao.update(role);
        EnumEntity updatedRole = roleDao.getEntityById(id);
        Assert.assertEquals(role, updatedRole);
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testDelete() throws DAOLayerException {
        simpleAdd_getEntity();
        roleDao.delete(entity.getId());
        EnumEntity role = roleDao.getEntityById(entity.getId());
        Assert.assertNull(role);
    }

    @Test
    public void testGetAll() {
        List<EnumEntity> roles = roleDao.getAll();
        Assert.assertTrue(roles.size() == 3);
    }

    @Test
    public void testGetEntityByName() throws DAOLayerException {
        simpleAdd_getEntity();
        EnumEntity role = roleDao.getEntityByName(entity.getValue());
        Assert.assertEquals(entity, role);
    }

}
