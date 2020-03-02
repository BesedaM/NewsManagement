package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.EnumEntityDTO;
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
    public List<EnumEntityDTO> getRoles() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EnumEntityDTO getRole(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/add")
    public EnumEntityDTO addRole(@RequestParam("role") String name) throws ServiceLayerException {
        EnumEntityDTO role = new EnumEntityDTO(name);
        this.service.add(role);
        return role;
    }

    @PutMapping("/")
    public EnumEntityDTO updateRole(@RequestParam int id, @RequestParam String name) throws ServiceLayerException {
        EnumEntityDTO tag = service.getDtoById(id);
        if (name != null) {
            tag.setName(name);
            service.update(tag);
        }
        return tag;
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable int id) {
        service.delete(id);
        return ROLE_WITH_ID + id + IS_DELETED;
    }
}
