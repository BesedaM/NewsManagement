package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.exception.NotEnoughArgumentsException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.serviceclass.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.lab.beseda.util.ControllerMessage.*;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/all")
    public List<UserDTO> getUsers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/")
    public UserDTO addUser(@RequestBody UserDTO userDTO) throws ServiceLayerException {
        service.add(userDTO);
        return userDTO;
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") int id, @RequestBody UserDTO user) throws ServiceLayerException {
        user.setId(id);
        return service.update(user);
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        service.delete(id);
        return USER_WITH_ID + id + IS_DELETED;
    }
}
