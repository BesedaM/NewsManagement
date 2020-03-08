package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.entity.User;
import com.epam.lab.beseda.service.serviceclass.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
public class UserController extends AbstractController<User, UserDTO> {

    @Autowired
    @Override
    protected void setService(AbstractService<User, UserDTO> service) {
        this.service = service;
    }

}
