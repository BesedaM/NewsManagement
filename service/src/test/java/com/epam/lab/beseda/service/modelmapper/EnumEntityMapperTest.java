package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.configuration.ModelMapperConfiguration;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.exception.MapperException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ModelMapperConfiguration.class})
public class EnumEntityMapperTest {

    @Autowired
//    @Qualifier("enumEntityMapper")
    private EnumEntityMapper mapper;

    @Test
    public void testToEntity() throws MapperException {
        String name = "1111";
        EnumEntityDTO enumEntityDTO = new EnumEntityDTO(name);
        EnumEntity enumEntity = mapper.toEntity(enumEntityDTO);
        Assert.assertEquals(name, enumEntity.getName());
    }

    @Test
    public void testToDto() {
        String name = "1111";
        EnumEntity enumEntity = new EnumEntity(name);
        EnumEntityDTO enumEntityDTO = mapper.toDto(enumEntity);
        Assert.assertEquals(name, enumEntityDTO.getName());
    }

}
