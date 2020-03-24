package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.RoleDAOInterface;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.util.DatabaseConfigure;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
    private RoleDAOInterface roleDao;

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

    @Test
    public void testAdd_01() {
        Role entity = new Role("driver");
        int id = roleDao.add(entity);
        Role role = roleDao.getEntityById(id);
        Assert.assertEquals(entity, role);
    }

    @Test
    public void testAdd_02() {
        Role entity = new Role("hairdresser");
        int id01 = roleDao.add(entity);
        int id02 = roleDao.add(entity);
        Assert.assertEquals(id01, id02);
    }

    @Test
    public void testUpdate() {
        Role entity = new Role("barber");
        int id = roleDao.add(entity);
        String newName = "MMM";

        Role role = roleDao.getEntityById(id);
        role.setName(newName);
        roleDao.update(role);
        Role updatedRole = roleDao.getEntityById(id);
        Assert.assertEquals(role, updatedRole);
    }

    @Test
    public void testGetEntityById() {
        Role entity = new Role("assistant");
        int id = roleDao.add(entity);
        Role role = roleDao.getEntityById(id);
        Assert.assertEquals(entity, role);
    }

    @Test
    public void testGetEntityById_noSuchEntity() {
        Role role = roleDao.getEntityById(1000);
        Assert.assertNull(role);
    }

    @Test
    public void testDelete() {
        int id = roleDao.add(new Role("policeman"));
        roleDao.delete(id);
        Role role = roleDao.getEntityById(id);
        Assert.assertNull(role);
    }

    @Test
    public void testGetAll() {
        List<Role> roles = roleDao.getAll();
        Assert.assertEquals(3, roles.size());
    }

    @Test
    public void testGetEntityByName() {
        String name="woodcutter";
        roleDao.add(new Role(name));
        Role role = roleDao.getEntityByName(name);
        Assert.assertEquals(name, role.getName());
    }

    @Test
    public void testGetEntityByName_noSuchName() {
        Role role = roleDao.getEntityByName("no_name");
        Assert.assertNull(role);
    }

}
