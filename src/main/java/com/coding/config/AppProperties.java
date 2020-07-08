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

    private String endPoint = "http://mobile-fix.cdn.coding-space.cn";
    private String ak = "3LKtRSyD26qE2b49zDMljLMOPthqQT4dg1qLP5DA";
    private String sk = "u5mxMAVvySYZwI8vtSkbaoEGvwYo4Ae7r_tibd3u";
    private String bn = "mobile-fix";
}
