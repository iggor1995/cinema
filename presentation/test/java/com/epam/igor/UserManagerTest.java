package com.epam.igor;

import com.epam.igor.api.UserService;
import com.epam.igor.entity.UserManager;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserManager userManager;


}
