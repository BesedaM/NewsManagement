package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.TagDAO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.exception.ValidationException;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.validator.TagValidator;
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
public class TagServiceTest {

    private static List<EnumEntity> tags;
    private static List<EnumEntityDTO> tagsDTO;
    private static EnumEntity tag;
    private static EnumEntityDTO tagDTO;

    @Mock
    private TagDAO tagDAO;

    @Mock
    private EnumEntityMapper mapper;

    @Mock
    private TagValidator tagValidator;

    @InjectMocks
    private TagService service;

    @BeforeClass
    public static void init() {
        tags = new ArrayList<>();
        tags.add(new EnumEntity(1, "one"));
        tags.add(new EnumEntity(2, "two"));
        tags.add(new EnumEntity(3, "three"));

        tagsDTO = new ArrayList<>();
        tagsDTO.add(new EnumEntityDTO(1, "one"));
        tagsDTO.add(new EnumEntityDTO(2, "two"));
        tagsDTO.add(new EnumEntityDTO(3, "three"));

        tag = new EnumEntity();
        tagDTO = new EnumEntityDTO();
    }


    @Test
    public void testGetAll() {
        Mockito.when(tagDAO.getAll()).thenReturn(tags);
        Mockito.when(mapper.toDto(any(EnumEntity.class))).thenReturn(new EnumEntityDTO());

        List<EnumEntityDTO> receivedRoles = service.getAll();
        Mockito.verify(tagDAO, times(1)).getAll();
        Assert.assertEquals(receivedRoles.size(), 3);
    }


    @Test
    public void testGetEntityById() {
        int id = 1;
        Mockito.when(tagDAO.getEntityById(id)).thenReturn(tag);
        Mockito.when(mapper.toDto(any(EnumEntity.class))).thenReturn(tagDTO);
        EnumEntityDTO role = service.getDtoById(id);
        Mockito.verify(tagDAO, times(1)).getEntityById(id);
        Assert.assertEquals(role, tagDTO);
    }

    @Test
    public void testDelete() {
        int id = 1;
        Mockito.doNothing().when(tagDAO).delete(id);
        service.delete(id);
        Mockito.verify(tagDAO, times(1)).delete(id);
    }

    @Test
    public void testAdd_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(tagValidator).validate(any(EnumEntityDTO.class));
        Mockito.when(tagDAO.add(any(EnumEntity.class))).thenReturn(1);
        Mockito.when(mapper.toEntity(any(EnumEntityDTO.class))).thenReturn(tag);

        service.add(tagDTO);
        Mockito.verify(tagDAO, times(1)).add(tag);
        Mockito.verify(tagValidator, times(1)).validate(tagDTO);
    }

    @Test(expected = ServiceLayerException.class)
    public void testAdd_incorrectData() throws ServiceLayerException, DAOLayerException {
        Mockito.doThrow(new ValidationException()).when(tagValidator).validate(tagDTO);

        service.add(tagDTO);
        Mockito.verify(tagDAO, never()).add(tag);
        Mockito.verify(tagValidator, times(1)).validate(tagDTO);
    }

    @Test
    public void testUpdate_correctData() throws DAOLayerException, ServiceLayerException {
        Mockito.doNothing().when(tagValidator).validate(any(EnumEntityDTO.class));
        Mockito.doNothing().when(tagDAO).update(any(EnumEntity.class));
        Mockito.when(mapper.toEntity(any(EnumEntityDTO.class))).thenReturn(tag);

        service.update(tagDTO);
        Mockito.verify(tagValidator, times(1)).validate(any(EnumEntityDTO.class));
        Mockito.verify(tagDAO, times(1)).update(any(EnumEntity.class));
    }

    @Test(expected = ServiceLayerException.class)
    public void testUpdate_incorrectData() throws DAOLayerException, ServiceLayerException {
        Mockito.doThrow(ValidationException.class).when(tagValidator).validate(any(EnumEntityDTO.class));
        service.update(tagDTO);
        Mockito.verify(tagValidator, times(1)).validate(tagDTO);
        Mockito.verify(tagDAO, never()).update(tag);
    }

    @Test
    public void testGetEntityByName() {
        String name = "two";
        when(tagDAO.getEntityByName(name)).thenReturn(tag);
        EnumEntityDTO entity = service.getEntityByName(name);

        verify(tagDAO, atMostOnce()).getEntityByName(anyString());
    }
}
