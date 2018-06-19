package com.epam.igor.impl;

import com.epam.igor.api.UserService;
import com.epam.igor.dao.api.UserDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.User;
import com.epam.igor.exception.ServiceException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless()
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByName(String username) throws ServiceException {
        try {
            return userDao.findByName(username);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find user with username -" + username, e);
        }
    }
}