package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.AbstractDAO;
import com.epam.lab.beseda.dao.entitydao.RoleDAO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.validator.RoleValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService extends EnumEntityService {

    public RoleService() {
    }

    public RoleService(RoleDAO dao, RoleValidator validator, EnumEntityMapper mapper) {
        super(dao, validator,mapper);
    }

    @Autowired
    @Qualifier("roleDao")
    @Override
    protected void setDao(AbstractDAO<EnumEntity> dao) {
        this.dao = dao;
    }

    @Autowired
    @Qualifier("roleValidator")
    @Override
    protected void setValidator(Validatable<EnumEntityDTO> validator) {
        this.validator = validator;
    }
}
