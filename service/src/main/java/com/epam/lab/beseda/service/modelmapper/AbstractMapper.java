package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.BaseDTO;
import com.epam.lab.beseda.entity.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public abstract class AbstractMapper<E extends BaseEntity, M extends BaseDTO> implements Mapper<E, M> {

    @Autowired
    protected ModelMapper modelMapper;

    protected Class<E> entityClass;
    protected Class<M> dtoClass;


    protected AbstractMapper(Class<E> entityClass, Class<M> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(M dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, entityClass);
    }

    @Override
    public M toDto(E entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, dtoClass);
    }

}
