package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDAO<E extends BaseEntity> implements AbstractDAOInterface<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<E> className;


    protected AbstractDAO() {
    }

    protected AbstractDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected abstract void setClass();

    @Transactional(readOnly = true)
    @Override
    public List<E> getAll() {
        return entityManager.createQuery(getAllStatement(), className).getResultList();
    }

    @Transactional
    @Override
    public abstract int add(E entity);

    @Transactional
    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public E getEntityById(int id) {
        return entityManager.find(className, id);
    }

    @Transactional
    @Override
    public void delete(int id) {
        E entity = entityManager.find(className, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }


    /**
     * Return string representation of SQL 'select all' query.
     */
    protected abstract String getAllStatement();

}
