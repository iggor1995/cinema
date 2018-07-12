package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.UserDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.Movie;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.Serializable;

@Dependent
@Named("userDaoJPA")
public class UserDaoJPA implements UserDao, Serializable {

    private static final long serialVersionUID = 7304243809121174813L;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoJPA.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByName(String name) throws DaoException {
        LOGGER.info("Find by user name - " + name);
        User user;
        Query query = entityManager.createQuery("SELECT user from User user where user.name LIKE :value");
        query.setParameter("value", name);
        user = (User) query.getSingleResult();
        if (user == null) {
            throw new DaoException("Have no such user");
        }
        return user;
    }

    @Override
    public User create(User user, UserAccount userAccount) throws DaoException {
        try {
            entityManager.persist(user);
            entityManager.flush();
            LOGGER.debug("Persist user - " + user);
            userAccount.setUserId(user.getId());
            entityManager.persist(userAccount);
            entityManager.flush();
            LOGGER.debug("Persist user account");
        } catch (PersistenceException e) {
            throw new DaoException("Not enough information for persist user", e);
        }
        return user;
    }

    @Override
    public UserAccount getUserAccountByUserId(long userId) throws DaoException {
        UserAccount userAccount;
        LOGGER.debug("Retrieving user account");
        Query query = entityManager.createQuery("SELECT userAccount from UserAccount userAccount where userAccount.userId LIKE :value");
        query.setParameter("value", userId);
        userAccount = (UserAccount) query.getSingleResult();
        if (userAccount == null) {
            throw new DaoException("Have no such account");
        }
        return userAccount;
    }

    @Override
    public void updateAccount(UserAccount userAccount) throws DaoException {
        LOGGER.debug("Update user account");
        entityManager.merge(userAccount);
    }

    @Override
    public void delete(User user) throws DaoException {
        entityManager.remove(user);
    }

    @Override
    public User findByID(long id) throws DaoException {
        return entityManager.find(User.class, id);

    }
}
