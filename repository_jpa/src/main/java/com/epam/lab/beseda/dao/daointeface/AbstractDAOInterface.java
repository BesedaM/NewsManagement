package com.epam.lab.beseda.dao.daointeface;


import com.epam.lab.beseda.exception.DAOLayerException;

import java.util.List;

public interface AbstractDAOInterface<E> {

    /**
     * Method for getting all the data from the specified table in the database
     *
     * @return list of E type objects
     */
    List<E> getAll();

    /**
     * Method for finding the specified entity by id.
     *
     * @param id entity id in database
     * @return the specified object or null if no object was found
     */
    E getEntityById(int id);

    /**
     * Deletes object specified by id from database.
     *
     * @param id entity id
     */
    void delete(int id);

    /**
     * Method for adding object to database.
     *
     * @param entity the object to add
     * @return database-generated entity id
     */
    int add(E entity);

    /**
     * Updates data of specified object.
     *
     * @param entity the object to update
     */
    void update(E entity);

}
