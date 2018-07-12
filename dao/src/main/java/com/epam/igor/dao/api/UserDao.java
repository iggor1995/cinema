package com.epam.igor.dao.api;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserAccount;

public interface UserDao {

    /**
     * Method finds all user entities in database and chose first
     *
     * @param name of user
     * @return found user
     * @throws DaoException if have no any matches
     */
    User findByName(String name) throws DaoException;

    User create(User user, UserAccount userAccount) throws DaoException;

    UserAccount getUserAccountByUserId(long userId) throws DaoException;

    void updateAccount(UserAccount userAccount) throws DaoException;

    void delete(User user) throws DaoException;

    User findByID(long id) throws DaoException;

}
