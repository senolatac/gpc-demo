package com.example.gpcdemo.service;

import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ListUsersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author sa
 * @date 26.04.2022
 * @time 13:18
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService
{
    private final AndroidPublisher androidPublisher;

    public void getUsers() throws IOException
    {
        ListUsersResponse response = androidPublisher.users()
                .list("developers/*")
                .execute();

        log.info("Users: {}", response.getUsers().size());
    }
}
