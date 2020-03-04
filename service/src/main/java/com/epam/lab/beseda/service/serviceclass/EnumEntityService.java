package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.AbstractDAO;
import com.epam.lab.beseda.dao.entitydao.EnumEntityDAO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.serviceinterface.EnumEntityServiceInterface;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public abstract class EnumEntityService extends AbstractService<EnumEntity, EnumEntityDTO> implements EnumEntityServiceInterface {

    protected EnumEntityService() {
    }

    protected EnumEntityService(AbstractDAO<EnumEntity> dao, Validatable<EnumEntityDTO> validator, EnumEntityMapper mapper) {
        super(dao, validator, mapper);
    }

    @Autowired
    @Qualifier("enumEntityMapper")
    protected void setMapper(Mapper<EnumEntity, EnumEntityDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public EnumEntityDTO getEntityByName(String name) {
        EnumEntity enumEntity = ((EnumEntityDAO) dao).getEntityByName(name.toLowerCase());
        return mapper.toDto(enumEntity);
    }

}
