package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.RoleDAO;
import com.epam.lab.beseda.dao.entitydao.UserDAO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.entity.User;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.modelmapper.UserMapper;
import com.epam.lab.beseda.service.validator.UserValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static List<User> users;
    private static List<UserDTO> userDTOList;
    private static User user;
    private static UserDTO userDTO;

    @Mock
    private UserDAO userDao;

    @Mock
    private RoleDAO roleDAO;

    @Mock
    private EnumEntityMapper enumEntityMapper;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserValidator validator;

    @InjectMocks
    private UserService service;

    @BeforeClass
    public static void init() {
        users = new ArrayList<>();
        users.add(new User("Andrei", "Drobak", "drovnil", "1234rth"));
        users.add(new User("Tmofei", "Pakatov", "tima44", "54gjhb222"));
        users.add(new User("Anna", "Nikolaeva", "nika21", "jjkbhj6"));

        userDTOList = new ArrayList<>();
        userDTOList.add(new UserDTO("Andrei", "Drobak", "drovnil", "1234rth", "guest"));
        userDTOList.add(new UserDTO("Tmofei", "Pakatov", "tima44", "54gjhb222", "guest"));
        userDTOList.add(new UserDTO("Anna", "Nikolaeva", "nika21", "jjkbhj6", "guest"));

        user = new User();
        userDTO = new UserDTO();
    }

    @Test
    public void testGetAll() {
        when(userDao.getAll()).thenReturn(users);
        Mockito.when(mapper.toDto(any(User.class))).thenReturn(userDTO);
        Mockito.when(service.getRole(anyInt())).thenReturn(new EnumEntityDTO());

        List<UserDTO> receivedUsers = service.getAll();
        Mockito.verify(userDao, times(1)).getAll();
        Assert.assertEquals(receivedUsers.size(), 3);
    }


    @Test
    public void testGetEntityById() {
        int id = 1;
        when(userDao.getEntityById(id)).thenReturn(user);
        Mockito.when(mapper.toDto(any(User.class))).thenReturn(userDTO);
        Mockito.when(service.getRole(anyInt())).thenReturn(new EnumEntityDTO());

        UserDTO user = service.getDtoById(id);
        Mockito.verify(userDao, times(1)).getEntityById(id);
        Assert.assertEquals(user, userDTO);
    }

    @Test
    public void testDelete() {
        int id = 1;
        Mockito.doNothing().when(userDao).delete(id);
        service.delete(id);
        Mockito.verify(userDao, times(1)).delete(id);
    }

    @Test
    public void testAdd_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(validator).validate(any(UserDTO.class));
        Mockito.when(mapper.toEntity(userDTOList.get(1))).thenReturn(user);
        Mockito.when(roleDAO.getEntityByName(anyString())).thenReturn(new EnumEntity());
        when(userDao.add(any(User.class))).thenReturn(1);
        Mockito.doNothing().when(userDao).setRole(anyInt(), anyInt());

        service.add(userDTOList.get(1));
        Mockito.verify(userDao, times(1)).add(user);
        Mockito.verify(validator, times(1)).validate(userDTOList.get(1));
        Mockito.verify(userDao, times(1)).setRole(anyInt(), anyInt());
    }

    @Test(expected = ServiceLayerException.class)
    public void testAdd_incorrectData() throws ServiceLayerException, DAOLayerException {
        Mockito.doThrow(new ValidationException()).when(validator).validate(any(UserDTO.class));

        service.add(userDTO);
        Mockito.verify(userDao, never()).add(user);
        Mockito.verify(validator, times(1)).validate(any(UserDTO.class));
    }

    @Test
    public void testUpdate_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(validator).validate(any(UserDTO.class));
        Mockito.doNothing().when(userDao).update(any(User.class));
        Mockito.when(mapper.toEntity(userDTOList.get(1))).thenReturn(user);
        Mockito.doNothing().when(userDao).setRole(anyInt(), anyInt());
        Mockito.when(roleDAO.getEntityByName(anyString())).thenReturn(new EnumEntity());

        service.update(userDTOList.get(1));
        Mockito.verify(validator, times(1)).validate(any(UserDTO.class));
        Mockito.verify(userDao, times(1)).update(any(User.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testUpdate_incorrectData() throws DAOLayerException, ServiceLayerException {
        Mockito.doThrow(ValidationException.class).when(validator).validate(any(UserDTO.class));
        service.update(userDTO);
        Mockito.verify(validator, times(1)).validate(userDTO);
        Mockito.verify(userDao, never()).update(any(User.class));
    }


    @Test
    public void testGetUserByLoginAndPassword() {
        Mockito.when(mapper.toDto(any(User.class))).thenReturn(userDTO);
        when(userDao.getUserByLoginAndPassword(anyString(), anyString())).thenReturn(user);
        Mockito.when(userDao.getRole(anyInt())).thenReturn(new EnumEntity());

        UserDTO receivedUser = service.getUserByLoginAndPassword("gg", "123");
        verify(userDao, atMostOnce()).getUserByLoginAndPassword(anyString(), anyString());
        Assert.assertEquals(userDTO, receivedUser);
    }

    @Test
    public void testGetUserByLoginAndPassword_no_such_user() {
        when(userDao.getUserByLoginAndPassword(anyString(), anyString())).thenReturn(null);
        Mockito.when(mapper.toDto(null)).thenReturn(null);

        UserDTO receivedUser = service.getUserByLoginAndPassword("gg", "123");
        Assert.assertNull(receivedUser);
        verify(userDao, atMostOnce()).getUserByLoginAndPassword(anyString(), anyString());
    }

    @Test
    public void testGetUserByLogin() {
        when(userDao.getUserByLogin(anyString())).thenReturn(user);
        Mockito.when(mapper.toDto(any(User.class))).thenReturn(userDTO);
        Mockito.when(userDao.getRole(anyInt())).thenReturn(new EnumEntity());

        UserDTO receivedUser = service.getUserByLogin("12546");
        verify(userDao, atMostOnce()).getUserByLogin(anyString());
        Assert.assertEquals(userDTO, receivedUser);
    }

    @Test
    public void testGetUserByLogin_no_such_user() {
        when(userDao.getUserByLogin(anyString())).thenReturn(null);
        Mockito.when(mapper.toDto(null)).thenReturn(null);

        UserDTO receivedUser = service.getUserByLogin("gg");
        verify(userDao, atMostOnce()).getUserByLogin(anyString());
        Assert.assertNull(receivedUser);
    }

    @Test
    public void testSetRole() {
        doNothing().when(userDao).setRole(anyInt(), anyInt());
        service.setRole(1, 2);
        verify(userDao, atMostOnce()).setRole(anyInt(), anyInt());
    }

    @Test
    public void testGetRole() {
        Mockito.when(enumEntityMapper.toDto(any(EnumEntity.class))).thenReturn(new EnumEntityDTO());
        EnumEntity role = new EnumEntity();
        when(userDao.getRole(anyInt())).thenReturn(role);
        EnumEntityDTO receivedRole = service.getRole(1);
        verify(userDao, atMostOnce()).getRole(anyInt());
        Assert.assertEquals(new EnumEntityDTO(), receivedRole);
    }

}
