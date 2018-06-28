package com.epam.igor.impl;


import com.epam.igor.api.UserService;
import com.epam.igor.dao.api.UserDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserAccount;
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

    @Override
    public void createUser(User user) throws ServiceException {
        try {
            UserAccount userAccount = new UserAccount();
            userAccount.setCash(5000);
            userDao.create(user, userAccount);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create user", e);
        }
    }

    public boolean chargeUser(User user, double price) throws ServiceException {
        try {
            UserAccount userAccount = getUserAccount(user.getId());
            if(userAccount != null && userAccount.getCash() > price) {
                userAccount.setCash(userAccount.getCash() - price);
                userDao.updateAccount(userAccount);
            }
        } catch (DaoException e) {
            throw new ServiceException("Couldn't update user account: user Id -> " + user.getId());
        }
        return false;
    }

    public UserAccount getUserAccount(long userId) throws ServiceException {
        try {
           return  userDao.getUserAccountByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Couldn't get user account");
        }
    }
}
