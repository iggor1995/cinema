package com.epam.igor;

import com.epam.igor.entity.User;
import com.epam.igor.entity.UserRole;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class UserTest {

    private User user;

    @Before
    public void initEvent() {
        user = new User();
        user.setName("Igor");
        Set<UserRole> roles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        roles.add(userRole);
        user.setUserRole(roles);
        user.setEnabled(true);
        user.setPassword("asdasd");
    }

    @Test
    public void shouldReturnTrue(){
        assert (true);
    }


}
