package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.exception.NotEnoughArgumentsException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.serviceclass.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.lab.beseda.util.ControllerMessage.*;

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
    public AuthorDTO addAuthor(@RequestBody AuthorDTO author) throws ServiceLayerException, NotEnoughArgumentsException {
        if (author.getName() != null && author.getSurname() != null) {
            this.service.add(author);
        } else {
            throw new NotEnoughArgumentsException(FULL_AUTHOR_DATA_REQUIRED);
        }
        return author;
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable("id") int id, @RequestBody AuthorDTO author) throws ServiceLayerException, NotEnoughArgumentsException {
        AuthorDTO authorDTO = service.getDtoById(id);
        if (author.getName() != null) {
            authorDTO.setName(author.getName());
        }
        if (author.getSurname() != null) {
            authorDTO.setSurname(author.getSurname());
        }
        service.update(authorDTO);
        return authorDTO;
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable("id") int id) {
        service.delete(id);
        return AUTHOR_WITH_ID + id + IS_DELETED;
    }

}
