package com.epam.igor;

import com.epam.igor.dao.api.UserDao;
import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserAccount;
import com.epam.igor.entity.UserRole;
import com.epam.igor.exception.ServiceException;
import com.epam.igor.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDao mockedUserDao;

    @InjectMocks
    private UserServiceImpl userService;

    private User givenUser;
    private UserAccount userAccount;

    @Before
    public void init(){
        userAccount = new UserAccount();
        userAccount.setCash(5000);
        givenUser = new User();
        givenUser.setId(5L);
        givenUser.setName(UUID.randomUUID().toString());
        String password = UUID.randomUUID().toString();
        givenUser.setPassword(password);
        givenUser.setEnabled(true);
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        givenUser.setUserRole(roles);
    }

    @Test
    public void getUserByNameTest() throws DaoException, ServiceException {
        String name = UUID.randomUUID().toString();
        givenUser.setName(name);
        Mockito.when(mockedUserDao.findByName(any(String.class))).thenReturn(givenUser);
        User returnUser = userService.getUserByName(name);
        System.out.println(returnUser);
        assertEquals(name, returnUser.getName());
    }

    @Test
    public void createUserTest() throws DaoException, ServiceException {
        Mockito.when(mockedUserDao.create(any(User.class), any(UserAccount.class))).thenReturn(givenUser);
        Long registeredId = userService.createUser(givenUser);
        assertEquals(registeredId, givenUser.getId());
    }

    @Test
    public void findByIdTest() throws DaoException, ServiceException {
        Mockito.when(mockedUserDao.findByID(any(Long.class))).thenReturn(givenUser);
        User resultUser = userService.findById(5L);

        assertEquals(resultUser.getName(), givenUser.getName());
        assertEquals(resultUser.getPassword(), givenUser.getPassword());
        assertEquals(resultUser.getUserRole(), givenUser.getUserRole());
        assertEquals(resultUser.getId(), givenUser.getId());
    }

    @Test
    public void chargeUserTest() throws DaoException, ServiceException {
        Double charge = 500.0;
        Double userCash = 3000.0;
        userAccount.setCash(userCash);
        Mockito.when(mockedUserDao.getUserAccountByUserId(any(Long.class))).thenReturn(userAccount);
        assert(userService.chargeUser(givenUser, charge));

        verify(mockedUserDao).updateAccount(any(UserAccount.class));
    }

}
