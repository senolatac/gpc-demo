package com.example.gpcdemo.config;

import com.example.gpcdemo.helper.PublisherHelper;
import com.google.api.services.androidpublisher.AndroidPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sa
 * @date 26.04.2022
 * @time 12:54
 */
@Configuration
@RequiredArgsConstructor
public class PublisherConfig
{

    private final PublisherHelper publisherHelper;

    @Bean
    public AndroidPublisher androidPublisher()
    {
        return publisherHelper.init();
    }
}
