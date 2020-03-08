package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.serviceclass.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.lab.beseda.util.ControllerMessage.AUTHOR_WITH_ID;
import static com.epam.lab.beseda.util.ControllerMessage.IS_DELETED;

@RestController
@RequestMapping("/authors/")
public class AuthorController {

    @Autowired
    private AuthorService service;


    @GetMapping("/all")
    public List<AuthorDTO> getAuthors() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthor(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/")
    public AuthorDTO addAuthor(@RequestBody AuthorDTO author) throws ServiceLayerException {
        service.add(author);
        return author;
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable("id") int id, @RequestBody AuthorDTO author) throws ServiceLayerException {
        author.setId(id);
        return service.update(author);
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable("id") int id) {
        service.delete(id);
        return AUTHOR_WITH_ID + id + IS_DELETED;
    }

}
