package com.coding.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guanweiming
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        AppProperties.class,
})
public class AppConfiguration {
    private final AppProperties appProperties;

    public AppConfiguration(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public AppProperties appProperties() {
        return appProperties;
    }
}
