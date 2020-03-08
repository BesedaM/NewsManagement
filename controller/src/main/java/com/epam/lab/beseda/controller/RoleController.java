package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.RoleDTO;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.service.serviceclass.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles/")
public class RoleController extends AbstractController<Role, RoleDTO> {

    @Autowired
    @Override
    protected void setService(AbstractService<Role, RoleDTO> service) {
        this.service = service;
    }

}
