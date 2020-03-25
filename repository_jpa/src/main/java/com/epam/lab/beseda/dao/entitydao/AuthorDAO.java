package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.AuthorDAOInterface;
import com.epam.lab.beseda.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static com.epam.lab.beseda.util.JpaParametersName.JPA_NAME;
import static com.epam.lab.beseda.util.JpaParametersName.JPA_SURNAME;
import static com.epam.lab.beseda.util.Query.SELECT_ALL_AUTHORS;
import static com.epam.lab.beseda.util.Query.SELECT_AUTHOR_BY_NAME_SURNAME;

@Transactional
@Repository
public class AuthorDAO extends AbstractDAO<Author> implements AuthorDAOInterface {

    public AuthorDAO() {
    }

    public AuthorDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @Autowired
    protected void setClass() {
        this.className = Author.class;
    }

    @Override
    public int add(Author entity) {
        int id = 0;
        Author existentEntity = this.getAuthorByNameSurname(entity.getName(), entity.getSurname());
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
    public Author getAuthorByNameSurname(String name, String surname) {
        Author author = null;
        try {
            author = (Author) entityManager.createQuery(SELECT_AUTHOR_BY_NAME_SURNAME).setParameter(JPA_NAME, name).setParameter(JPA_SURNAME, surname).getSingleResult();
        } catch (
                NoResultException ignored) {
        }
        return author;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_AUTHORS;
    }

}
