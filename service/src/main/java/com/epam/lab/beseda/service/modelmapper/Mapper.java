package com.epam.lab.beseda.service.modelmapper;

import com.epam.lab.beseda.dto.BaseDTO;
import com.epam.lab.beseda.entity.BaseEntity;
import com.epam.lab.beseda.exception.MapperException;

public interface Mapper<E extends BaseEntity,M extends BaseDTO> {

    E toEntity(M dto) throws MapperException;

    M toDto(E entity);
}
