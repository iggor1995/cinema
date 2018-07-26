package com.epam.igor.soap;

import com.epam.igor.api.UserService;
import com.epam.igor.entity.User;
import com.epam.igor.exception.ServiceException;
import com.epam.igor.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@Stateless
@LocalBean
@WebService(endpointInterface = "com.epam.igor.soap.UserWs")
public class UserWsImpl implements UserWs {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private UserService userService;


    @WebResult(name = "testUser")
    @WebMethod(operationName = "getUser")
    public User getUserById(long id){
        try {
            return userService.findById(id);
        } catch (ServiceException e) {
            LOGGER.error("get user by id");
        }
        return null;
    }
}
