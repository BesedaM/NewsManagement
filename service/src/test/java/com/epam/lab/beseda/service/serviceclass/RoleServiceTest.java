package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.RoleDAO;
import com.epam.lab.beseda.dto.RoleDTO;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import com.epam.lab.beseda.service.modelmapper.RoleMapper;
import com.epam.lab.beseda.service.validator.RoleValidator;
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
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    private static List<Role> roles;
    private static List<RoleDTO> rolesDTO;
    private static RoleDTO roleDTO;
    private static Role role;

    @Mock
    private RoleDAO roleDao;

    @Mock
    private RoleValidator validator;

    @Mock
    private RoleMapper mapper;

    @InjectMocks
    private RoleService service;

    @BeforeClass
    public static void init() {
        roles = new ArrayList<>();
        roles.add(new Role(1, "one"));
        roles.add(new Role(2, "two"));
        role = new Role(1, "one");

        rolesDTO = new ArrayList<>();
        rolesDTO.add(new RoleDTO(1, "one"));
        rolesDTO.add(new RoleDTO(1, "two"));
        roleDTO = new RoleDTO(1, "one");
    }


    @Test
    public void testGetAll() {
        Mockito.when(roleDao.getAll()).thenReturn(roles);
        Mockito.when(mapper.toDto(isA(Role.class))).thenReturn(new RoleDTO());
        List<RoleDTO> receivedRoles = service.getAll();
        Mockito.verify(roleDao, times(1)).getAll();
        Assert.assertEquals(receivedRoles.size(), 2);
    }


    @Test
    public void testGetEntityById() {
        int id = 1;
        Mockito.when(roleDao.getEntityById(id)).thenReturn(role);
        Mockito.when(mapper.toDto(isA(Role.class))).thenReturn(roleDTO);
        RoleDTO role = service.getDtoById(id);
        Mockito.verify(roleDao, times(1)).getEntityById(id);
        Mockito.verify(mapper, times(1)).toDto(isA(Role.class));
        Assert.assertEquals(role, roleDTO);
    }

    @Test
    public void testDelete() {
        int id = 1;
        Mockito.doNothing().when(roleDao).delete(id);
        service.delete(id);
        Mockito.verify(roleDao, times(1)).delete(id);
    }

    @Test
    public void testAdd_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(validator).validate(any(RoleDTO.class));
        Mockito.when(roleDao.add(any(Role.class))).thenReturn(1);
        Mockito.when(mapper.toEntity(any(RoleDTO.class))).thenReturn(role);

        service.add(roleDTO);
        Mockito.verify(roleDao, times(1)).add(any(Role.class));
        Mockito.verify(validator, times(1)).validate(any(RoleDTO.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testAdd_incorrectData() throws ServiceLayerException, DAOLayerException {
        Mockito.doThrow(new ValidationException()).when(validator).validate(roleDTO);

        service.add(roleDTO);
        Mockito.verify(roleDao, never()).add(any(Role.class));
        Mockito.verify(validator, times(1)).validate(any(RoleDTO.class));
    }

    @Test
    public void testUpdate_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.when(roleDao.getEntityById(anyInt())).thenReturn(role);
        Mockito.doNothing().when(validator).validate(any(RoleDTO.class));
        Mockito.doNothing().when(roleDao).update(any(Role.class));
        Mockito.when(mapper.toEntity(any(RoleDTO.class))).thenReturn(role);
        service.update(roleDTO);
        Mockito.verify(validator, times(1)).validate(any(RoleDTO.class));
        Mockito.verify(roleDao, times(1)).update(any(Role.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testUpdate_incorrectData() throws DAOLayerException, ServiceLayerException {
        Mockito.when(roleDao.getEntityById(anyInt())).thenReturn(role);
        Mockito.doThrow(ValidationException.class).when(validator).validate(any(RoleDTO.class));
        service.update(roleDTO);
        Mockito.verify(validator, times(1)).validate(roleDTO);
        Mockito.verify(roleDao, never()).update(any(Role.class));
    }

    @Test
    public void testGetEntityByName() {
        String name = "two";
        Mockito.when(mapper.toDto(any(Role.class))).thenReturn(roleDTO);
        when(roleDao.getEntityByName(name)).thenReturn(role);
        RoleDTO entity = service.getEntityByName(name);

        verify(roleDao, atMostOnce()).getEntityByName(anyString());
        Assert.assertEquals(roleDTO, entity);
    }

}
