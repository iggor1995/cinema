package com.epam.igor.entity;

import com.epam.igor.api.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

@ManagedBean
@RequestScoped
public class UserManager {

    @Inject
    private UserService userService;
}
