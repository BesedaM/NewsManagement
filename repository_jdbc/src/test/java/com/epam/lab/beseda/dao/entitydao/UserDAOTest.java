package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.UserDAOInterface;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.entity.User;
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
public class UserDAOTest {

    @Autowired
    private DatabaseConfigure dbc;

    private static User entity;

    @Autowired
    private UserDAOInterface userDao;

    @BeforeClass
    public static void initData() {
        entity = new User("Egor", "Kalinin", "kalina", "12345");
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
        int id = userDao.add(entity);
        entity.setId(id);
        User user = userDao.getEntityById(id);
        Assert.assertEquals(entity, user);
    }

    @Test
    public void testAdd() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testUpdate() throws DAOLayerException {
        int id = userDao.add(entity);
        String password = "MMM";

        User user = userDao.getEntityById(id);
        user.setPassword(password);
        userDao.update(user);
        User updatedUser = userDao.getEntityById(user.getId());
        Assert.assertEquals(user, updatedUser);
    }

    @Test
    public void testGetEntityById() throws DAOLayerException {
        simpleAdd_getEntity();
    }

    @Test
    public void testGetEntityById_noSuchUser() throws DAOLayerException {
        User user = userDao.getEntityById(1000);
        Assert.assertNull(user);
    }

    @Test
    public void testDelete() throws DAOLayerException {
        simpleAdd_getEntity();
        userDao.delete(entity.getId());
        User user = userDao.getEntityById(entity.getId());
        Assert.assertNull(user);
    }

    @Test
    public void testGetAll() {
        List<User> userList = userDao.getAll();
        Assert.assertTrue(userList.size() > 0);
    }

    @Test
    public void getUserByLoginAndPassword() throws DAOLayerException {
        int id = userDao.add(entity);
        entity.setId(id);
        User user = userDao.getUserByLoginAndPassword(entity.getLogin(), entity.getPassword());
        Assert.assertEquals(entity, user);
    }

    @Test
    public void getUserByLoginAndPassword_noSuchUser() throws DAOLayerException {
        int id = userDao.add(entity);
        entity.setId(id);
        User user = userDao.getUserByLoginAndPassword(entity.getLogin(), "qqq");
        Assert.assertNull(user);
    }


    @Test
    public void getUserBylogin() throws DAOLayerException {
        int id = userDao.add(entity);
        entity.setId(id);
        User user = userDao.getUserByLogin(entity.getLogin());
        Assert.assertEquals(entity, user);
    }

    @Test
    public void getUserBylogin_noSuchUser() throws DAOLayerException {
        User user = userDao.getUserByLogin("1254");
        Assert.assertNull(user);
    }

    @Test
    public void testSetGetRole() throws DAOLayerException {
        int id = userDao.add(entity);
        entity.setId(id);

        int roleId = 3;
        userDao.setRole(id, roleId);
        EnumEntity actualRole = userDao.getRole(id);
        Assert.assertEquals(roleId, actualRole.getId());
    }


}
