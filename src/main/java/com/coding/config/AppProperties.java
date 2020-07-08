package com.coding.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author guanweiming
 */
@Data
@ConfigurationProperties("app")
public class AppProperties {
    /**
     * appId
     */
    private String appId;
}
