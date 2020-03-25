package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dao.daointeface.AuthorDAOInterface;
import com.epam.lab.beseda.dto.AuthorDTO;
import com.epam.lab.beseda.entity.Author;
import com.epam.lab.beseda.service.modelmapper.AuthorMapper;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.serviceinterface.AuthorServiceInterface;
import com.epam.lab.beseda.service.validator.AuthorValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends AbstractService<Author, AuthorDTO> implements AuthorServiceInterface {

    public AuthorService() {
        super();
    }

    public AuthorService(AuthorDAOInterface authorDAO, AuthorValidator validator, AuthorMapper mapper) {
        super(authorDAO, validator, mapper);
    }

    @Autowired
    @Override
    protected void setDao(AbstractDAOInterface<Author> dao) {
        this.dao = dao;
    }

    @Autowired
    @Qualifier("authorValidator")
    @Override
    protected void setValidator(Validatable<AuthorDTO> validator) {
        this.validator = validator;
    }

    @Autowired
    @Qualifier("authorMapper")
    @Override
    protected void setMapper(Mapper<Author, AuthorDTO> mapper) {
        this.mapper = mapper;
    }


    @Override
    public AuthorDTO update(AuthorDTO dto) {
        Author oldEntity = dao.getEntityById(dto.getId());
        if (oldEntity != null) {
            if (dto.getName() != null) {
                oldEntity.setName(dto.getName());
            }
            if (dto.getSurname() != null) {
                oldEntity.setSurname(dto.getSurname());
            }
            dao.update(oldEntity);
        } else {
            dto = null;
        }
        return dto;
    }

}
