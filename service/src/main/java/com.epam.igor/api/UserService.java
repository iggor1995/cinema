package com.epam.igor.api;

import com.epam.igor.entity.User;
import com.epam.igor.exception.ServiceException;

import javax.ejb.Local;

@Local
public interface UserService {

    User getUserByName(String name) throws ServiceException;

    long createUser(User user) throws ServiceException;

    boolean chargeUser(User user, double price) throws ServiceException;

    User findById(long id) throws ServiceException;

}
