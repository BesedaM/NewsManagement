package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.configuration.ModelMapperConfiguration;
import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.entity.User;
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
public class UserMapperTest {

    @Autowired
//    @Qualifier("userMapper")
    private UserMapper mapper;

    private UserDTO userDTO;
    private User user;

    {
        userDTO = new UserDTO("Alex", "Kooler", "boler", "boler123", "new_role_name");
        user = new User("Dzianis", "Kliaver", "klever", "123456");
    }

    @Test
    public void testToEntity() throws MapperException {
        User user = mapper.toEntity(userDTO);
        Assert.assertEquals(userDTO.getLogin(), user.getLogin());
    }

    @Test
    public void testToDto() {
        UserDTO userDTO = mapper.toDto(user);
        Assert.assertEquals(user.getLogin(), userDTO.getLogin());
    }
}
