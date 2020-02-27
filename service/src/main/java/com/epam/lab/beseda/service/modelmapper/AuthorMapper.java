package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.dto.AuthorDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper extends AbstractMapper<Author, AuthorDTO> {

    public AuthorMapper() {
        super(Author.class, AuthorDTO.class);
    }

}
