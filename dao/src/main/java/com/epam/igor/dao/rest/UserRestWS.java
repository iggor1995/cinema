package com.epam.igor.dao.rest;

import com.epam.igor.dao.api.UserDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.dao.impl.UserDaoJPA;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserAccount;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/users")
@Stateless
@LocalBean
public class UserRestWS {

    private UserDao userDao;

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") long id) {
        try {
            return userDao.findByID(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    public void addUser(User user) throws DaoException {
        userDao.create(user, new UserAccount());
    }
}
