package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.EnumEntityDTO;
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
    public List<EnumEntityDTO> getTags() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EnumEntityDTO getTag(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/add")
    public EnumEntityDTO addTag(@RequestParam("tag") String name) throws ServiceLayerException {
        EnumEntityDTO tag = new EnumEntityDTO(name);
        this.service.add(tag);
        return tag;
    }

    @PutMapping("/")
    public EnumEntityDTO updateTag(@RequestParam int id, @RequestParam String name) throws ServiceLayerException {
        EnumEntityDTO tag = service.getDtoById(id);
        if (name != null) {
            tag.setName(name);
            service.update(tag);
        }
        return tag;
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable("id") int id) {
        service.delete(id);
        return TAG_WITH_ID + id + IS_DELETED;
    }

}
