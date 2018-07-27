package com.epam.igor;

import com.epam.igor.api.UserService;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserRole;
import com.epam.igor.exception.ServiceException;
import com.epam.igor.weld.WeldJUnit4Runner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.*;
import static junit.framework.Assert.assertEquals;

//@RunWith(WeldJUnit4Runner.class)
public class UserServiceImplTest {

    private UserDaoMock userDaoMock;

    @Inject
    private UserService userService;

    @Before
    public void init() throws DaoException {
        User user = new User();
        user.setName("Igor");
        user.setPassword("asd");
        user.setEnabled(true);
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        user.setUserRole(roles);

        User user1 = new User();
        user1.setName("Vasya");
        user1.setPassword("asd");
        user1.setEnabled(true);
        user1.setUserRole(roles);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userDaoMock = new UserDaoMock(userList);
        userDaoMock.init();
    }

    @After
    public void cleanup() throws DaoException {
        userDaoMock.cleanup();
    }

    //@Test
    public void testRegister() throws ServiceException {
        String name = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEnabled(true);
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        user.setUserRole(roles);

        long userId = userService.createUser(user);

        assertEquals(userService.getUserByName(user.getName()), userService.findById(userId));
    }

}
