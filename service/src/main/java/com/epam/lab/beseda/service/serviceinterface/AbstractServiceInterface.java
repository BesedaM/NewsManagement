package com.epam.lab.beseda.service.serviceinterface;

import com.epam.lab.beseda.exception.ServiceLayerException;

import java.util.List;

public interface AbstractServiceInterface<D> {

    List<D> getAll();

    D getDtoById(int id);

    void add(D entity) throws ServiceLayerException;

    void update(D entity) throws ServiceLayerException;

    void delete(int entityId);
}
