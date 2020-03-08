package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.service.serviceclass.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags/")
public class TagController extends AbstractController<Tag, TagDTO> {

    @Autowired
    @Override
    protected void setService(AbstractService<Tag, TagDTO> service) {
        this.service = service;
    }

}
