package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.RoleDAO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.exception.validation.ValidationException;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
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

    private static List<EnumEntity> roles;
    private static List<EnumEntityDTO> rolesDTO;
    private static EnumEntityDTO roleDTO;
    private static EnumEntity role;

    @Mock
    private RoleDAO roleDao;

    @Mock
    private RoleValidator validator;

    @Mock
    private EnumEntityMapper mapper;

    @InjectMocks
    private RoleService service;

    @BeforeClass
    public static void init() {
        roles = new ArrayList<>();
        roles.add(new EnumEntity(1, "one"));
        roles.add(new EnumEntity(2, "two"));
        role = new EnumEntity();

        rolesDTO = new ArrayList<>();
        rolesDTO.add(new EnumEntityDTO(1, "one"));
        rolesDTO.add(new EnumEntityDTO(1, "two"));
        roleDTO = new EnumEntityDTO();
    }


    @Test
    public void testGetAll() {
        Mockito.when(roleDao.getAll()).thenReturn(roles);
        Mockito.when(mapper.toDto(isA(EnumEntity.class))).thenReturn(new EnumEntityDTO());
        List<EnumEntityDTO> receivedRoles = service.getAll();
        Mockito.verify(roleDao, times(1)).getAll();
        Assert.assertEquals(receivedRoles.size(), 2);
    }


    @Test
    public void testGetEntityById() {
        int id = 1;
        Mockito.when(roleDao.getEntityById(id)).thenReturn(role);
        Mockito.when(mapper.toDto(isA(EnumEntity.class))).thenReturn(roleDTO);
        EnumEntityDTO role = service.getDtoById(id);
        Mockito.verify(roleDao, times(1)).getEntityById(id);
        Mockito.verify(mapper, times(1)).toDto(isA(EnumEntity.class));
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
        Mockito.doNothing().when(validator).validate(any(EnumEntityDTO.class));
        Mockito.when(roleDao.add(any(EnumEntity.class))).thenReturn(1);
        Mockito.when(mapper.toEntity(any(EnumEntityDTO.class))).thenReturn(role);

        service.add(roleDTO);
        Mockito.verify(roleDao, times(1)).add(any(EnumEntity.class));
        Mockito.verify(validator, times(1)).validate(any(EnumEntityDTO.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testAdd_incorrectData() throws ServiceLayerException, DAOLayerException {
        Mockito.doThrow(new ValidationException()).when(validator).validate(roleDTO);

        service.add(roleDTO);
        Mockito.verify(roleDao, never()).add(any(EnumEntity.class));
        Mockito.verify(validator, times(1)).validate(any(EnumEntityDTO.class));
    }

    @Test
    public void testUpdate_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(validator).validate(any(EnumEntityDTO.class));
        Mockito.doNothing().when(roleDao).update(any(EnumEntity.class));
        Mockito.when(mapper.toEntity(any(EnumEntityDTO.class))).thenReturn(role);
        service.update(roleDTO);
        Mockito.verify(validator, times(1)).validate(any(EnumEntityDTO.class));
        Mockito.verify(roleDao, times(1)).update(any(EnumEntity.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testUpdate_incorrectData() throws DAOLayerException, ServiceLayerException {
        Mockito.doThrow(ValidationException.class).when(validator).validate(any(EnumEntityDTO.class));
        service.update(roleDTO);
        Mockito.verify(validator, times(1)).validate(roleDTO);
        Mockito.verify(roleDao, never()).update(any(EnumEntity.class));
    }

    @Test
    public void testGetEntityByName() {
        String name = "two";
        Mockito.when(mapper.toDto(any(EnumEntity.class))).thenReturn(roleDTO);
        when(roleDao.getEntityByName(name)).thenReturn(role);
        EnumEntityDTO entity = service.getEntityByName(name);

        verify(roleDao, atMostOnce()).getEntityByName(anyString());
        Assert.assertEquals(roleDTO, entity);
    }

}
