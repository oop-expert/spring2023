package com.app.my.app.services;

import com.app.my.domain.models.User;
import com.app.my.domain.models.enums.Role;
import com.app.my.domain.repositories.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    //Проверка создания пользователя и его роли
    @Test
    void createUser() {
        User user = new User();
        boolean isUserCreated = userService.createUser(user);
        assertTrue(isUserCreated);
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.ROLE_USER)));
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    //Проверка на наличие пользователя
    @Test
    void addUserFailTest() {
        User user = new User();

        user.setEmail("user@gmail.com");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findByEmail("user@gmail.com");

        boolean isUserCreated = userService.createUser(user);

        assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));

    }
}