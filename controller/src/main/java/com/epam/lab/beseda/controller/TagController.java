package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.serviceclass.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.lab.beseda.util.ControllerMessage.IS_DELETED;
import static com.epam.lab.beseda.util.ControllerMessage.TAG_WITH_ID;

@RestController
@RequestMapping("/tags/")
public class TagController {

    @Autowired
    @Qualifier("tagService")
    private TagService service;

    @GetMapping("/all")
    public List<TagDTO> getTags() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TagDTO getTag(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/")
    public TagDTO addTag(@RequestParam("tag") String name) throws ServiceLayerException {
        TagDTO tag = new TagDTO(name.toLowerCase());
        service.add(tag);
        return tag;
    }

    @PutMapping("/")
    public TagDTO updateTag(@RequestParam int id, @RequestParam("tag") String name) throws ServiceLayerException {
        TagDTO tag = new TagDTO(id, name);
        return service.update(tag);
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable("id") int id) {
        service.delete(id);
        return TAG_WITH_ID + id + IS_DELETED;
    }

}
