package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.entitydao.AbstractDAO;
import com.epam.lab.beseda.dao.entitydao.RoleDAO;
import com.epam.lab.beseda.dao.entitydao.UserDAO;
import com.epam.lab.beseda.dto.EnumEntityDTO;
import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.entity.EnumEntity;
import com.epam.lab.beseda.entity.User;
import com.epam.lab.beseda.exception.EntityExistsException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.modelmapper.EnumEntityMapper;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.UserMapper;
import com.epam.lab.beseda.service.serviceinterface.UserServiceInterface;
import com.epam.lab.beseda.service.validator.UserValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.lab.beseda.util.ServiceConstants.USER_WITH_LOGIN_EXISTS;

@Service
public class UserService extends AbstractService<User, UserDTO> implements UserServiceInterface {

    public UserService() {
        super();
    }

    @Autowired
    @Qualifier("enumEntityMapper")
    private EnumEntityMapper enumEntityMapper;

    @Autowired
    @Qualifier("roleDao")
    private RoleDAO roleDAO;

    public UserService(UserDAO userDAO, RoleDAO roleDAO, UserValidator validator, UserMapper mapper, EnumEntityMapper enumEntityMapper) {
        super(userDAO, validator, mapper);
        this.roleDAO = roleDAO;
        this.enumEntityMapper = enumEntityMapper;
    }

    @Autowired
    @Override
    protected void setDao(AbstractDAO<User> dao) {
        this.dao = dao;
    }


    @Autowired
    @Qualifier("userValidator")
    @Override
    protected void setValidator(Validatable<UserDTO> validator) {
        this.validator = validator;
    }

    @Autowired
    @Qualifier("userMapper")
    @Override
    protected void setMapper(Mapper<User, UserDTO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOList = super.getAll();
        for (UserDTO userDTO :
                userDTOList) {
            EnumEntityDTO enumEntityDTO = this.getRole(userDTO.getId());
            userDTO.setRole(enumEntityDTO.getName());
        }
        return userDTOList;
    }

    @Override
    public UserDTO getDtoById(int id) {
        UserDTO userDTO = super.getDtoById(id);
        if (userDTO != null) {
            EnumEntityDTO enumEntityDTO = this.getRole(userDTO.getId());
            userDTO.setRole(enumEntityDTO.getName());
        }
        return userDTO;
    }

    @Override
    public void add(UserDTO dto) throws ServiceLayerException {
        if (((UserDAO) dao).getUserByLogin(dto.getLogin()) == null) {
            super.add(dto);
            EnumEntity enumEntity = roleDAO.getEntityByName(dto.getRole());
            ((UserDAO) dao).setRole(dto.getId(), enumEntity.getId());
        } else {
            throw new EntityExistsException(USER_WITH_LOGIN_EXISTS);
        }
    }

    @Override
    public void update(UserDTO dto) throws ServiceLayerException {
        super.update(dto);
        EnumEntity enumEntity = roleDAO.getEntityByName(dto.getRole());
        ((UserDAO) dao).setRole(dto.getId(), enumEntity.getId());
    }

    @Override
    public UserDTO getUserByLoginAndPassword(String login, String password) {
        User user = ((UserDAO) dao).getUserByLoginAndPassword(login, password);
        UserDTO userDTO = mapper.toDto(user);
        if (userDTO != null) {
            EnumEntity role = ((UserDAO) dao).getRole(user.getId());
            userDTO.setRole(role.getName());
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        User user = ((UserDAO) dao).getUserByLogin(login);
        UserDTO userDTO = mapper.toDto(user);
        if (userDTO != null) {
            EnumEntity role = ((UserDAO) dao).getRole(user.getId());
            userDTO.setRole(role.getName());
        }
        return userDTO;
    }

    @Override
    public void setRole(int userId, int roleId) {
        ((UserDAO) dao).setRole(userId, roleId);
    }

    @Override
    public EnumEntityDTO getRole(int userId) {
        EnumEntity role = ((UserDAO) dao).getRole(userId);
        return enumEntityMapper.toDto(role);
    }
}
