package com.rogers.account.management.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Account Management Service API")
                        .description("Basic CRUD Operation for Account Management")
                        .version("V-1.0"));
    }
}
