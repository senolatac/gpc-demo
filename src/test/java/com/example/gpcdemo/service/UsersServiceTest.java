package com.example.gpcdemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sa
 * @date 26.04.2022
 * @time 13:21
 */
@SpringBootTest
class UsersServiceTest
{

    @Autowired
    private UsersService usersService;

    @Test
    void getUsers() throws IOException
    {
        usersService.getUsers();
    }
}
