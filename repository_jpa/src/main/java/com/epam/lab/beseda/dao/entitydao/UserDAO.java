package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.RoleDAOInterface;
import com.epam.lab.beseda.dao.daointeface.UserDAOInterface;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static com.epam.lab.beseda.util.JpaParametersName.JPA_LOGIN;
import static com.epam.lab.beseda.util.JpaParametersName.JPA_PASSWORD;
import static com.epam.lab.beseda.util.Query.*;

@Transactional
@Repository
public class UserDAO extends AbstractDAO<User> implements UserDAOInterface {

    @Autowired
    private RoleDAOInterface roleDAO;


    public UserDAO() {
    }

    public UserDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @Autowired
    protected void setClass() {
        this.className = User.class;
    }

    @Override
    public int add(User entity) {
        int id = 0;
        User existentEntity = this.getUserByLogin(entity.getLogin());
        if (existentEntity != null) {
            id = existentEntity.getId();
            entity.setId(id);
        } else {
            Role role = entity.getRole();
            roleDAO.add(role);
            entityManager.persist(entity);
            id = entity.getId();
        }
        return id;
    }

    @Override
    public void update(User entity) {
        Role role = entity.getRole();
        if (role.getId() == 0) {
            roleDAO.add(role);
        }
        entityManager.merge(entity);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        try {
            user = (User) entityManager.createQuery(SELECT_USER_BY_LOGIN_AND_PASSWORD).setParameter(JPA_LOGIN, login).setParameter(JPA_PASSWORD, password).getSingleResult();
        } catch (NoResultException ignored) {
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = (User) entityManager.createQuery(SELECT_USER_BY_LOGIN).setParameter(JPA_LOGIN, login).getSingleResult();
        } catch (NoResultException ignored) {
        }
        return user;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_USERS;
    }
}
