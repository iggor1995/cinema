package com.epam.igor.entity;

import com.epam.igor.api.UserService;
import com.epam.igor.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.Set;

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

    @PostConstruct
    public void init() {
            this.user = new User();
    }

    public String saveUser(User user) {
        try {
            userService.createUser(getUserWithDefaultValues(user));
        } catch (ServiceException e) {
            LOGGER.error("Cannot save user");
        }
        return HOME;
    }

    private User getUserWithDefaultValues(User user){
        UserRole defaultUserRole = new UserRole();
        defaultUserRole.setId(2L);
        defaultUserRole.setRole("ROLE_USER");

        Set<UserRole> roles = new HashSet<>();
        roles.add(defaultUserRole);
        user.setUserRole(roles);
        user.setEnabled(true);
        return user;
    }
}
