package com.rogers.account.management.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
