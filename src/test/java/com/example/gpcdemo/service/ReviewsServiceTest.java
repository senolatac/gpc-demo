package com.example.gpcdemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sa
 * @date 26.04.2022
 * @time 13:11
 */
@SpringBootTest
class ReviewsServiceTest
{
    private static final String REVIEW_ID = "gp:AOqpTOHGZZgUDSWrbvdsPewbBwIlupe2leQpAZZjACtVfzKTU_Af_vzWNhvBUC5ysuZANaO74cMaJ2TCaDRvuXI";

    @Autowired
    private ReviewsService reviewsService;

    @Test
    void getReviews() throws IOException
    {
        reviewsService.getReviews();
    }

    @Test
    void getReviewById() throws IOException
    {
        reviewsService.getReviewById(REVIEW_ID);
    }

    @Test
    void reply() throws IOException
    {
        reviewsService.reply(REVIEW_ID);
    }
}
