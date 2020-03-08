package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.service.serviceclass.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors/")
public class AuthorController extends AbstractController<Author, AuthorDTO> {

    @Autowired
    @Override
    protected void setService(AbstractService<Author, AuthorDTO> service) {
        this.service = service;
    }

}
