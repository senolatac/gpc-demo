package com.example.gpcdemo.service;

import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ConvertRegionPricesRequest;
import com.google.api.services.androidpublisher.model.Money;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author sa
 * @date 26.04.2022
 * @time 13:34
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MonetizationService
{
    private final AndroidPublisher androidPublisher;

    @Value("${app.package.name}")
    public String ANDROID_PACKAGE_NAME;

    public void getMonetization() throws IOException
    {
        ConvertRegionPricesRequest request = new ConvertRegionPricesRequest()
                .setPrice(new Money().setCurrencyCode("USD").setUnits(9L));

        var response = androidPublisher.monetization()
                .convertRegionPrices(ANDROID_PACKAGE_NAME, request)
                .execute();

        log.info("Response details: {}", response);
    }
}
