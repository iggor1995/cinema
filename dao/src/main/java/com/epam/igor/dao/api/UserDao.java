package com.epam.igor.dao.api;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.User;

public interface UserDao {

    /**
     * Method finds all user entities in database and chose first
     *
     * @param name of user
     * @return found user
     * @throws DaoException if have no any matches
     */
    User findByName(String name) throws DaoException;

    User create(User user) throws DaoException;
}
