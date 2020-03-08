package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.RoleDTO;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.serviceclass.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.lab.beseda.util.ControllerMessage.IS_DELETED;
import static com.epam.lab.beseda.util.ControllerMessage.ROLE_WITH_ID;

@RestController
@RequestMapping("/roles/")
public class RoleController {

    @Autowired
    @Qualifier("roleService")
    private RoleService service;

    @GetMapping("/all")
    public List<RoleDTO> getRoles() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RoleDTO getRole(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/")
    public RoleDTO addRole(@RequestParam("role") String name) throws ServiceLayerException {
        RoleDTO role = new RoleDTO(name.toLowerCase());
        service.add(role);
        return role;
    }

    @PutMapping("/")
    public RoleDTO updateRole(@RequestParam int id, @RequestParam String name) throws ServiceLayerException {
        RoleDTO role = new RoleDTO(id, name);
        return service.update(role);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable int id) {
        service.delete(id);
        return ROLE_WITH_ID + id + IS_DELETED;
    }
}
