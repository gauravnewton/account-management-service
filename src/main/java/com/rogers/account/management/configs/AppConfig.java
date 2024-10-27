package com.rogers.account.management.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {

    @Value("${address.lookup.baseurl}")
    private String addressLookUpUrl;
}
