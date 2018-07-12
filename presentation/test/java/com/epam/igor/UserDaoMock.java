package com.epam.igor;

import com.epam.igor.dao.exception.DaoException;
import com.epam.igor.dao.impl.UserDaoJPA;
import com.epam.igor.entity.User;
import com.epam.igor.entity.UserAccount;

import java.util.List;

public class UserDaoMock extends UserDaoJPA {

    private final List<User> users;

    public UserDaoMock(List<User> users){
        this.users = users;
    }

    public void init() throws DaoException {
        cleanup();
        UserAccount userAccount = new UserAccount();
        userAccount.setCash(5000);
        users.forEach((user) -> {
            try {
                create(user, userAccount);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        });

    }

    public void cleanup() throws DaoException {
        for(User user: users){
            delete(findByName(user.getName()));
        }
    }
}
