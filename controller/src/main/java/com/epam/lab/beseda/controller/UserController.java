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
    public UserDTO addUser(@RequestBody UserDTO userDTO) throws ServiceLayerException, NotEnoughArgumentsException {
        if (userDTO.getName() == null
                || userDTO.getSurname() == null
                || userDTO.getLogin() == null
                || userDTO.getPassword() == null
                || userDTO.getRole() == null) {
            throw new NotEnoughArgumentsException(FULL_USER_DATA_REQUIRED);
        }
        this.service.add(userDTO);
        return userDTO;
    }

    @PutMapping("/")
    public UserDTO updateUser(@RequestBody UserDTO user) throws ServiceLayerException {
        UserDTO userDTO = service.getDtoById(user.getId());
        if (user.getName() != null) {
            userDTO.setName(user.getName());
        }
        if (user.getSurname() != null) {
            userDTO.setSurname(user.getSurname());
        }
        if (user.getLogin() != null) {
            userDTO.setLogin(user.getLogin());
        }
        if (user.getPassword() != null) {
            userDTO.setPassword(user.getPassword());
        }
        if (user.getRole() != null) {
            userDTO.setRole(user.getRole());
        }
        service.update(userDTO);
        return userDTO;
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        service.delete(id);
        return USER_WITH_ID + id + IS_DELETED;
    }
}
