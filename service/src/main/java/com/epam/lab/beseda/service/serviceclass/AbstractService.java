package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dto.BaseDTO;
import com.epam.lab.beseda.entity.BaseEntity;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.serviceinterface.AbstractServiceInterface;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AbstractService<K extends BaseEntity, D extends BaseDTO> implements AbstractServiceInterface<D> {

    protected AbstractDAOInterface<K> dao;

    protected Validatable<D> validator;

    protected Mapper<K, D> mapper;


    protected AbstractService() {
    }

    protected AbstractService(AbstractDAOInterface<K> dao, Validatable<D> validator, Mapper<K, D> mapper) {
        this.mapper = mapper;
        this.dao = dao;
        this.validator = validator;
    }

    protected abstract void setDao(AbstractDAOInterface<K> dao);

    protected abstract void setValidator(Validatable<D> validator);

    protected abstract void setMapper(Mapper<K, D> mapper);

    @Override
    public List<D> getAll() {
        List<D> dtoList = new ArrayList<D>();
        for (K entity : dao.getAll()) {
            D dto = mapper.toDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public D getDtoById(int id) {
        K entity = dao.getEntityById(id);
        return mapper.toDto(entity);
    }

    @Override
    public void add(D dto) throws ServiceLayerException {
        validator.validate(dto);
        try {
            int id = dao.add(mapper.toEntity(dto));
            dto.setId(id);
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void update(D dto) throws ServiceLayerException {
        validator.validate(dto);
        try {
            dao.update(mapper.toEntity(dto));
        } catch (DAOLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void delete(int entityId) {
        dao.delete(entityId);
    }

}
