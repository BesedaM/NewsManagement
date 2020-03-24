package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dao.daointeface.TagDAOInterface;
import com.epam.lab.beseda.dao.entitydao.TagDAO;
import com.epam.lab.beseda.dto.TagDTO;
import com.epam.lab.beseda.entity.Tag;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.TagMapper;
import com.epam.lab.beseda.service.serviceinterface.TagServiceInterface;
import com.epam.lab.beseda.service.validator.TagValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagService extends AbstractService<Tag, TagDTO> implements TagServiceInterface {

    public TagService() {
    }

    public TagService(TagDAOInterface dao, TagValidator validator, TagMapper mapper) {
        super(dao, validator, mapper);
    }

    @Autowired
    @Override
    protected void setDao(AbstractDAOInterface<Tag> dao) {
        this.dao = dao;
    }

    @Autowired
    @Override
    protected void setValidator(Validatable<TagDTO> validator) {
        this.validator = validator;
    }

    @Autowired
    protected void setMapper(Mapper<Tag, TagDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public TagDTO update(TagDTO dto) throws ServiceLayerException {
        Tag tag = dao.getEntityById(dto.getId());
        if (tag != null) {
            String name = dto.getName();
            dto.setName(name.toLowerCase());
            super.update(dto);
        } else {
            dto = null;
        }
        return dto;
    }

    @Override
    public TagDTO getEntityByName(String name) {
        Tag tag = ((TagDAO) dao).getEntityByName(name.toLowerCase());
        return mapper.toDto(tag);
    }
}
