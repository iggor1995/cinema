package com.epam.igor.api;


import com.epam.igor.entity.User;
import com.epam.igor.exception.ServiceException;

import javax.ejb.Local;

@Local
public interface UserService {

    /**
     * Method finds user by username using Dao layer
     *
     * @param username for searching
     * @return user with selected username
     * @throws ServiceException if cannot find user
     */
    User getUserByName(String username) throws ServiceException;
}
