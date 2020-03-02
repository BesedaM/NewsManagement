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
public class RoleDAOTest {

    @Autowired
    @Qualifier("roleDao")
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
        EnumEntity role = roleDao.getEntityById(id);
        Assert.assertEquals(entity, role);
    }


    @Test
    public void testAdd_01() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testAdd_02() throws DAOLayerException {
        int id01 = roleDao.add(entity);
        int id02 = roleDao.add(entity);
        Assert.assertEquals(id01,id02);
    }

    @Test
    public void testUpdate() throws DAOLayerException {
        int id = roleDao.add(entity);
        String newName = "MMM";

        EnumEntity role = roleDao.getEntityById(id);
        role.setName(newName);
        roleDao.update(role);
        EnumEntity updatedRole = roleDao.getEntityById(id);
        Assert.assertEquals(role, updatedRole);
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testGetEntityById_noSuchEntity() throws DAOLayerException {
        EnumEntity role = roleDao.getEntityById(1000);
        Assert.assertNull(role);
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
        EnumEntity role = roleDao.getEntityByName(entity.getName());
        Assert.assertEquals(entity, role);
    }

    @Test
    public void testGetEntityByName_noSuchName() throws DAOLayerException {
        simpleAdd_getEntity();
        EnumEntity role = roleDao.getEntityByName("no_name");
        Assert.assertNull(role);
    }

}
