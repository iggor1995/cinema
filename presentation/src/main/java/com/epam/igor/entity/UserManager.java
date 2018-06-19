package com.epam.igor.entity;

import com.epam.igor.api.UserService;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ManagedBean
@RequestScoped
public class UserManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    private static final String HOME = "/pages/home?faces-redirect=true";

    @Inject
    private UserService userService;

    private User user;

    @Produces
    @Named
    public User getUser() {
        return user;
    }

    public String saveUser(User user) {
        try {
            userService.createUser(user);
        } catch (ServiceException e) {
            LOGGER.error("Cannot create new user", e);
        }
        return HOME;
    }

}
