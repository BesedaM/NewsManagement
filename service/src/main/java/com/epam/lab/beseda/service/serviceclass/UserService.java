package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dao.daointeface.UserDAOInterface;
import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.entity.User;
import com.epam.lab.beseda.exception.EntityExistsException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.UserMapper;
import com.epam.lab.beseda.service.serviceinterface.UserServiceInterface;
import com.epam.lab.beseda.service.validator.UserValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.epam.lab.beseda.util.ServiceConstants.USER_WITH_LOGIN_EXISTS;


@Service
public class UserService extends AbstractService<User, UserDTO> implements UserServiceInterface {


    public UserService() {
        super();
    }

    public UserService(UserDAOInterface userDAO, UserValidator validator, UserMapper mapper) {
        super(userDAO, validator, mapper);
    }

    @Autowired
    @Override
    protected void setDao(AbstractDAOInterface<User> dao) {
        this.dao = dao;
    }


    @Autowired
    @Override
    protected void setValidator(Validatable<UserDTO> validator) {
        this.validator = validator;
    }

    @Autowired
    @Override
    protected void setMapper(Mapper<User, UserDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void add(UserDTO dto) throws ServiceLayerException {
        validator.validate(dto);
        if (((UserDAOInterface) dao).getUserByLogin(dto.getLogin()) == null) {
            int id = dao.add(mapper.toEntity(dto));
            dto.setId(id);
        } else {
            throw new EntityExistsException(USER_WITH_LOGIN_EXISTS);
        }
    }

    @Override
    public UserDTO update(UserDTO dto) {

        User oldUser = dao.getEntityById(dto.getId());
        if (oldUser != null) {
            if (dto.getName() != null) {
                oldUser.setName(dto.getName());
            }
            if (dto.getSurname() != null) {
                oldUser.setSurname(dto.getSurname());
            }
            if (dto.getLogin() != null) {
                oldUser.setLogin(dto.getLogin());
            }
            if (dto.getPassword() != null) {
                oldUser.setPassword(dto.getPassword());
            }
            if (dto.getRole() != null) {
                oldUser.setRole(new Role(dto.getRole()));
            }
            dao.update(oldUser);
        }
        return mapper.toDto(oldUser);
    }

    @Override
    public UserDTO getUserByLoginAndPassword(String login, String password) {
        User user = ((UserDAOInterface) dao).getUserByLoginAndPassword(login, password);
        return mapper.toDto(user);
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        User user = ((UserDAOInterface) dao).getUserByLogin(login);
        return mapper.toDto(user);
    }

}
