package com.epam.lab.beseda.dao.entitydao;

import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
import com.epam.lab.beseda.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static com.epam.lab.beseda.util.JpaParametersName.JPA_NAME;
import static com.epam.lab.beseda.util.Query.GET_TAG_BY_NAME;
import static com.epam.lab.beseda.util.Query.SELECT_ALL_TAGS;

@Transactional
@Repository("tagDao")
public class TagDAO extends AbstractDAO<Tag> implements TagDAOInterface {

    public TagDAO() {
    }

    public TagDAO(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    @Autowired
    protected void setClass() {
        this.className = Tag.class;
    }

    @Override
    public int add(Tag entity) {
        int id = 0;
        Tag existentEntity = this.getEntityByName(entity.getName());
        if (existentEntity != null) {
            id = existentEntity.getId();
            entity.setId(id);
        } else {
            entityManager.persist(entity);
            id = entity.getId();
        }
        return id;
    }


    public Tag getEntityByName(String name) {
        Tag tag = null;
        try {
            tag = entityManager.createQuery(GET_TAG_BY_NAME, Tag.class).setParameter(JPA_NAME, name).getSingleResult();
        } catch (NoResultException ignored) {
        }
        return tag;
    }

    @Override
    protected String getAllStatement() {
        return SELECT_ALL_TAGS;
    }
}
