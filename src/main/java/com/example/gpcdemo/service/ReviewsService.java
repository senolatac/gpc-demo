package com.example.gpcdemo.service;

import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ReviewsListResponse;
import com.google.api.services.androidpublisher.model.ReviewsReplyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author sa
 * @date 26.04.2022
 * @time 11:26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewsService
{
    public static final long MAX_REVIEWS = 500;

    @Value("${app.package.name}")
    public String ANDROID_PACKAGE_NAME;

    private final AndroidPublisher androidPublisher;

    //Note: You can retrieve only the reviews that users have created or modified within the last week.
    public void getReviews() throws IOException
    {
        var reviews = androidPublisher.reviews();

        log.info("Reviews: {}", reviews);

        ReviewsListResponse response = reviews
                .list(ANDROID_PACKAGE_NAME)
                .setMaxResults(MAX_REVIEWS)
                .execute();

        for (var x : response.getReviews())
        {
            log.info("Review: {}", x);
        }
    }

    public void getReviewById(String reviewId) throws IOException
    {
        var review = androidPublisher.reviews().get(ANDROID_PACKAGE_NAME, reviewId);

        log.info("Review details: {}", review.execute());
    }

    public void reply(String reviewId) throws IOException
    {
        ReviewsReplyRequest request = new ReviewsReplyRequest()
                .setReplyText("Thank you");

        var response = androidPublisher.reviews()
                .reply(ANDROID_PACKAGE_NAME, reviewId, request)
                .execute();

        log.info("Review reponse details: {}", response);
    }
}
