package com.example.gpcdemo.helper;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.auth.Credentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author sa
 * @date 25.04.2022
 * @time 17:47
 */
@Slf4j
@Component
public class PublisherHelper
{

    private String RESOURCES_CLIENT_SECRETS_JSON;

    private String APP_NAME;

    private static JsonFactory JSON_FACTORY;
    private static HttpTransport HTTP_TRANSPORT;

    public PublisherHelper(@Value("${app.client-secrets.path}") String CLIENT_SECRET,
                           @Value("${app.name}") String APP_NAME)
    {
        this.RESOURCES_CLIENT_SECRETS_JSON = CLIENT_SECRET;
        this.APP_NAME = APP_NAME;
    }

    public AndroidPublisher init()
    {
        Credentials credentials = null;
        try
        {
            if (HTTP_TRANSPORT == null)
            {
                HTTP_TRANSPORT = newTrustedTransport();
            }

            if (JSON_FACTORY == null)
            {
                JSON_FACTORY = newJacksonFactory();
            }

            credentials = authorizeWithServiceAccount();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return build(HTTP_TRANSPORT, JSON_FACTORY, credentials, APP_NAME);
    }

    private AndroidPublisher build(HttpTransport transport,
                                   JsonFactory jsonFactory,
                                   Credentials googleCredentials,
                                   String applicationName)
    {
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(googleCredentials);

        return new AndroidPublisher.Builder(transport, jsonFactory, requestInitializer)
                .setApplicationName(applicationName)
                .build();
    }

    private HttpTransport newTrustedTransport() throws Exception
    {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    private JsonFactory newJacksonFactory()
    {
        return JacksonFactory.getDefaultInstance();
    }

    private Credentials authorizeWithServiceAccount() throws IOException
    {
        log.info("Authorizing using Service Account");


        return ServiceAccountCredentials
                .fromStream(new FileInputStream(RESOURCES_CLIENT_SECRETS_JSON))
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/androidpublisher"));



        /*
        return GoogleCredential.fromStream(new FileInputStream(RESOURCES_CLIENT_SECRETS_JSON))
                .createScoped(Collections.singleton(AndroidPublisherScopes.ANDROIDPUBLISHER));

         */

    }
}
