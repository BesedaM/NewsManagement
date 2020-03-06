package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dao.daointeface.EnumEntityDAOInterface;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.validator.TagValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagService extends EnumEntityService {

    public TagService() {
    }

    public TagService(EnumEntityDAOInterface dao, TagValidator validator, EnumEntityMapper mapper) {
        super(dao, validator,mapper);
    }

    @Autowired
    @Qualifier("tagDao")
    @Override
    protected void setDao(AbstractDAOInterface<EnumEntity> dao) {
        this.dao = dao;
    }

    @Autowired
    @Qualifier("tagValidator")
    @Override
    protected void setValidator(Validatable<EnumEntityDTO> validator) {
        this.validator = validator;
    }
}
