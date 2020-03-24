package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dao.entitydao.RoleDAO;
import com.epam.lab.beseda.dto.RoleDTO;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.RoleMapper;
import com.epam.lab.beseda.service.serviceinterface.RoleServiceInterface;
import com.epam.lab.beseda.service.validator.RoleValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService extends AbstractService<Role, RoleDTO> implements RoleServiceInterface {

    public RoleService() {
    }

    public RoleService(RoleDAO dao, RoleValidator validator, RoleMapper mapper) {
        super(dao, validator, mapper);
    }

    @Autowired
    @Override
    protected void setDao(AbstractDAOInterface<Role> dao) {
        this.dao = dao;
    }

    @Autowired
    @Override
    protected void setValidator(Validatable<RoleDTO> validator) {
        this.validator = validator;
    }

    @Autowired
    protected void setMapper(Mapper<Role, RoleDTO> mapper) {
        this.mapper = mapper;
    }


    @Override
    public RoleDTO update(RoleDTO dto) throws ServiceLayerException {
        Role role = dao.getEntityById(dto.getId());
        if (role != null) {
            String name = dto.getName();
            dto.setName(name.toLowerCase());
            super.update(dto);
        } else {
            dto = null;
        }
        return dto;
    }

    @Override
    public RoleDTO getEntityByName(String name) {
        Role enumEntity = ((RoleDAO) dao).getEntityByName(name.toLowerCase());
        return mapper.toDto(enumEntity);
    }
}
