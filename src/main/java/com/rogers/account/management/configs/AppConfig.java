/*
 * Copyright (c) 2024.
 * This is a assignment project by Gaurav Kumar for client assessment.
 */

package com.rogers.account.management.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The type App config.
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {

    @Value("${address.lookup.baseurl}")
    private String addressLookUpUrl;
    @Value("${developer.name}")
    private String developerName;
    @Value("${developer.email}")
    private String developerEmail;
    @Value("${developer.webUrl}")
    private String developerWebUrl;
}
