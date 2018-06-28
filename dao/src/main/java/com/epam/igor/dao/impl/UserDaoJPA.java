package com.epam.igor.dao.impl;

import com.epam.igor.dao.api.UserDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserAccount;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByName(String name) throws DaoException {
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
            userAccount.setUserId(user.getId());
            System.out.println(user.getId() + "iduid");
            entityManager.persist(userAccount);
            entityManager.flush();
        } catch (PersistenceException e) {
            throw new DaoException("Not enough information for persist user", e);
        }
        return user;
    }
}
