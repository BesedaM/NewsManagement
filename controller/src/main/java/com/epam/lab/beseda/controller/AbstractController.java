package com.epam.lab.beseda.controller;

import com.epam.lab.beseda.dto.BaseDTO;
import com.epam.lab.beseda.entity.BaseEntity;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.serviceclass.AbstractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.lab.beseda.util.ControllerMessage.ENTITY_WITH_ID;
import static com.epam.lab.beseda.util.ControllerMessage.IS_DELETED;

@RestController
public abstract class AbstractController<E extends BaseEntity, D extends BaseDTO> {

    protected AbstractService<E,D> service;

    protected abstract void setService(AbstractService<E,D> service);

    @GetMapping("/all")
    public List<D> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public D getOneDto(@PathVariable("id") int id) {
        return service.getDtoById(id);
    }

    @PostMapping("/")
    public D addDto(@RequestBody D dto) throws ServiceLayerException {
        service.add(dto);
        return service.getDtoById(dto.getId());
    }

    @PutMapping("/{id}")
    public D updateDto(@PathVariable("id") int id, @RequestBody D dto) throws ServiceLayerException {
        dto.setId(id);
        service.update(dto);
        return service.getDtoById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDto(@PathVariable("id") int id) {
        service.delete(id);
        return ENTITY_WITH_ID + id + IS_DELETED;
    }

}
