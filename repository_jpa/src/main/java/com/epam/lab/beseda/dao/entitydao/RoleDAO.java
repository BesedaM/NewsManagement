package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.RoleDAOInterface;
import com.epam.lab.beseda.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static com.epam.lab.beseda.util.JpaParametersName.JPA_NAME;
import static com.epam.lab.beseda.util.Query.GET_ROLE_BY_NAME;
import static com.epam.lab.beseda.util.Query.SELECT_ALL_ROLES;

@Transactional
@Repository("roleDao")
public class RoleDAO extends AbstractDAO<Role> implements RoleDAOInterface {

    public RoleDAO() {
    }

    public RoleDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @Autowired
    protected void setClass() {
        this.className = Role.class;
    }

    @Override
    public int add(Role entity) {
        int id = 0;
        Role existentEntity = this.getEntityByName(entity.getName());
        if (existentEntity != null) {
            id = existentEntity.getId();
            entity.setId(id);
        } else {
            entityManager.persist(entity);
            id = entity.getId();
        }
        return id;
    }


    @Override
    public Role getEntityByName(String name) {
        Role role = null;
        try {
            role = entityManager.createQuery(GET_ROLE_BY_NAME, Role.class).setParameter(JPA_NAME, name).getSingleResult();
        } catch (NoResultException ignored) {
        }
        return role;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_ROLES;
    }
}
