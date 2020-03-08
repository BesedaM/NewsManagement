package com.epam.lab.beseda.service.serviceclass;

import com.epam.lab.beseda.dao.daointeface.AbstractDAOInterface;
import com.epam.lab.beseda.dao.daointeface.RoleDAOInterface;
import com.epam.lab.beseda.dao.daointeface.UserDAOInterface;
import com.epam.lab.beseda.dao.entitydao.UserDAO;
import com.epam.lab.beseda.dto.RoleDTO;
import com.epam.lab.beseda.dto.UserDTO;
import com.epam.lab.beseda.entity.Role;
import com.epam.lab.beseda.entity.User;
import com.epam.lab.beseda.exception.DAOLayerException;
import com.epam.lab.beseda.exception.EntityExistsException;
import com.epam.lab.beseda.exception.ServiceLayerException;
import com.epam.lab.beseda.service.modelmapper.Mapper;
import com.epam.lab.beseda.service.modelmapper.RoleMapper;
import com.epam.lab.beseda.service.modelmapper.UserMapper;
import com.epam.lab.beseda.service.serviceinterface.UserServiceInterface;
import com.epam.lab.beseda.service.validator.UserValidator;
import com.epam.lab.beseda.service.validator.Validatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.lab.beseda.util.ServiceConstants.USER_WITH_LOGIN_EXISTS;

@Service
public class UserService extends AbstractService<User, UserDTO> implements UserServiceInterface {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleDAOInterface roleDAO;


    public UserService() {
        super();
    }

    public UserService(UserDAOInterface userDAO, RoleDAOInterface roleDAO, UserValidator validator, UserMapper mapper,
                       RoleMapper roleMapper) {
        super(userDAO, validator, mapper);
        this.roleDAO = roleDAO;
        this.roleMapper = roleMapper;
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
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOList = super.getAll();
        for (UserDTO userDTO :
                userDTOList) {
            RoleDTO enumEntityDTO = this.getRole(userDTO.getId());
            userDTO.setRole(enumEntityDTO.getName());
        }
        return userDTOList;
    }

    @Override
    public UserDTO getDtoById(int id) {
        UserDTO userDTO = super.getDtoById(id);
        if (userDTO != null) {
            RoleDTO enumEntityDTO = this.getRole(userDTO.getId());
            userDTO.setRole(enumEntityDTO.getName());
        }
        return userDTO;
    }

    @Override
    public void add(UserDTO dto) throws ServiceLayerException {
        validator.validate(dto);
        if (((UserDAO) dao).getUserByLogin(dto.getLogin()) == null) {
            super.add(dto);
            Role enumEntity = roleDAO.getEntityByName(dto.getRole());
            if (enumEntity == null) {
                enumEntity = new Role(dto.getRole());
                try {
                    roleDAO.add(enumEntity);
                } catch (DAOLayerException e) {
                    throw new ServiceLayerException(e);
                }
            }
            ((UserDAO) dao).setRole(dto.getId(), enumEntity.getId());
        } else {
            throw new EntityExistsException(USER_WITH_LOGIN_EXISTS);
        }
    }

    @Override
    public UserDTO update(UserDTO dto) throws ServiceLayerException {

        User oldUser = dao.getEntityById(dto.getId());
        if (oldUser != null) {
            if (dto.getName() == null) {
                dto.setName(oldUser.getName());
            }
            if (dto.getSurname() == null) {
                dto.setSurname(oldUser.getSurname());
            }
            if (dto.getLogin() == null) {
                dto.setLogin(oldUser.getLogin());
            }
            if (dto.getPassword() == null) {
                dto.setPassword(oldUser.getPassword());
            }
            if (dto.getRole() != null) {
                Role role = roleDAO.getEntityByName(dto.getRole());
                ((UserDAO) dao).setRole(dto.getId(), role.getId());
            }
            super.update(dto);
        } else {
            dto = null;
        }
        return dto;
    }

    @Override
    public UserDTO getUserByLoginAndPassword(String login, String password) {
        User user = ((UserDAO) dao).getUserByLoginAndPassword(login, password);
        UserDTO userDTO = mapper.toDto(user);
        if (userDTO != null) {
            Role role = ((UserDAO) dao).getRole(user.getId());
            userDTO.setRole(role.getName());
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        User user = ((UserDAO) dao).getUserByLogin(login);
        UserDTO userDTO = mapper.toDto(user);
        if (userDTO != null) {
            Role role = ((UserDAO) dao).getRole(user.getId());
            userDTO.setRole(role.getName());
        }
        return userDTO;
    }

    @Override
    public void setRole(int userId, int roleId) {
        ((UserDAO) dao).setRole(userId, roleId);
    }

    @Override
    public RoleDTO getRole(int userId) {
        Role role = ((UserDAO) dao).getRole(userId);
        return roleMapper.toDto(role);
    }
}
