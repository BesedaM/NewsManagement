package com.epam.lab.beseda.service.modelmapper;


import com.epam.lab.beseda.configuration.ModelMapperConfiguration;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.entity.Author;
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
public class AuthorMapperTest {

    @Autowired
//    @Qualifier("authorMapper")
    private AuthorMapper mapper;

    private AuthorDTO authorDTO;
    private Author author;

    {
        authorDTO = new AuthorDTO("Artur", "Pirozkov");


        author = new Author("Dzianis", "Melnik");
    }


    @Test
    public void testToEntity() throws MapperException {
        Author author = mapper.toEntity(authorDTO);
        Assert.assertEquals(authorDTO.getName(), author.getName());
    }

    @Test
    public void testToDto_01() {
        AuthorDTO authorDTO = mapper.toDto(author);
        Assert.assertEquals(author.getSurname(), authorDTO.getSurname());
    }

}
