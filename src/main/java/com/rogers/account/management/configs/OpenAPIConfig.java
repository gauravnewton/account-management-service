/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.rogers.account.management.commons.AppConstant.*;

/**
 * The type Open api config.
 */
@Configuration
public class OpenAPIConfig {
    @Autowired
    private AppConfig appConfig;

    /**
     * Registration open api open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(APP_TITLE)
                        .contact(getContact())
                        .description(APP_DESCRIPTION)
                        .version(VERSION));
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setName(appConfig.getDeveloperName());
        contact.setUrl(appConfig.getDeveloperWebUrl());
        contact.setEmail(appConfig.getDeveloperEmail());
        return contact;
    }
}
