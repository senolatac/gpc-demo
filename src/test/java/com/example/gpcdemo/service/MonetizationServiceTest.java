package com.example.gpcdemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sa
 * @date 26.04.2022
 * @time 13:38
 */
@SpringBootTest
class MonetizationServiceTest
{
    @Autowired
    private MonetizationService monetizationService;

    @Test
    void getMonetization() throws IOException
    {
        monetizationService.getMonetization();
    }
}
