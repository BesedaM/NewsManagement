package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.configuration.TestConfiguration;
import com.epam.lab.beseda.dao.daointeface.RoleDAOInterface;
import com.epam.lab.beseda.dao.daointeface.UserDAOInterface;
import com.epam.lab.beseda.entity.Role;
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

    @Autowired
    private UserDAOInterface userDao;

    @Autowired
    private RoleDAOInterface roleDao;

    @Before
    public void fillDatabase() {
        dbc.fillDatabase();
    }

    @After
    public void cleanDatabase() {
        dbc.cleanDatabase();
    }

    @Test
    public void testAdd() {
        User entity = new User("Egor", "Kalinin", "kalina", "12345",new Role("driver"));
        int id = userDao.add(entity);
        User user = userDao.getEntityById(id);
        Assert.assertEquals(entity, user);
    }

    @Test
    public void testUpdate() {
        User entity = new User("Maxim", "Kalinin", "max999", "12345",new Role("guest"));
        int id = userDao.add(entity);
        String password = "MMM67";
        String newRoleName="admin";

        User user = userDao.getEntityById(id);
        user.setPassword(password);
        user.setRole(new Role(newRoleName));
        userDao.update(user);
        User updatedUser = userDao.getEntityById(user.getId());
        Assert.assertEquals(user, updatedUser);

        int rolesNum=roleDao.getAll().size();
        Assert.assertEquals(3,rolesNum);
    }

    @Test
    public void testGetEntityById() {
        User entity = new User("Inga", "Jakova", "inga", "12UUU45",new Role("user"));
        int id = userDao.add(entity);
        User user = userDao.getEntityById(id);
        Assert.assertEquals(entity, user);
    }

    @Test
    public void testGetEntityById_noSuchUser() throws DAOLayerException {
        User user = userDao.getEntityById(1000);
        Assert.assertNull(user);
    }

    @Test
    public void testDelete() {
        User entity = new User("Inga", "Fominih", "inga09", "12UUU45",new Role("guest"));
        int id = userDao.add(entity);
        userDao.delete(id);
        User user = userDao.getEntityById(id);
        Assert.assertNull(user);
    }

    @Test
    public void testDelete_noSuchUser() {
        int id = -10;
        userDao.delete(id);
        User user = userDao.getEntityById(id);
        Assert.assertNull(user);
    }

    @Test
    public void testGetAll() {
        List<User> userList = userDao.getAll();
        Assert.assertTrue(userList.size() > 0);
    }

    @Test
    public void getUserByLoginAndPassword() {
        User entity = new User("Maria", "Gelich", "girl01", "12hho",new Role("author"));
        int id = userDao.add(entity);
        entity.setId(id);
        User user = userDao.getUserByLoginAndPassword(entity.getLogin(), entity.getPassword());
        Assert.assertEquals(entity, user);
        Assert.assertEquals(new Role("author"),user.getRole());
    }

    @Test
    public void getUserByLoginAndPassword_noSuchUser() {
        User entity = new User("Anna", "Frolova", "girl001", "12hho",new Role("author"));
        int id = userDao.add(entity);
        User user = userDao.getUserByLoginAndPassword(entity.getLogin(), "qqq");
        Assert.assertNull(user);
    }


    @Test
    public void getUserBylogin() {
        User entity = new User("Maria", "Gelich", "girl01", "12hho",new Role("author"));
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
    public void testAddUserCountRoles_sameNumber() throws DAOLayerException {
        User entity = new User("Egor", "Kalinin", "kalina", "12345",new Role("author"));
        userDao.add(entity);
        int rolesNum=roleDao.getAll().size();
        Assert.assertEquals(3,rolesNum);
    }

    @Test
    public void testAddUserCountRoles_additionalRole() throws DAOLayerException {
        User entity = new User("Egor", "Kalinin", "kalina", "12345",new Role("driver"));
        userDao.add(entity);
        int rolesNum=roleDao.getAll().size();
        Assert.assertEquals(4,rolesNum);
    }


}
