package com.epam.igor.impl;


import com.epam.igor.api.UserService;
import com.epam.igor.dao.api.UserDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserAccount;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless()
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByName(String username) throws ServiceException {
        try {
            LOGGER.info("Find by username - " + username);
            return userDao.findByName(username);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find user with username -" + username, e);
        }
    }

    @Override
    public void createUser(User user) throws ServiceException {
        try {
            LOGGER.info("Create user - " + user);
            UserAccount userAccount = new UserAccount();
            userAccount.setCash(5000);
            userDao.create(user, userAccount);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create user", e);
        }
    }

    public boolean chargeUser(User user, double price) throws ServiceException {
        try {
            LOGGER.info("Charge user - " + user + " with price - " + price);
            UserAccount userAccount = getUserAccount(user.getId());
            if(userAccount != null && userAccount.getCash() > price) {
                userAccount.setCash(userAccount.getCash() - price);
                userDao.updateAccount(userAccount);
                return true;
            }
            else{
                System.out.println(userAccount.getUserId() + " " + userAccount.getCash() + " " + price);
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
