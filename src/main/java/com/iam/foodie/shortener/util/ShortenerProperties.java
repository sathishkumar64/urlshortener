package com.iam.foodie.shortener.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(ignoreUnknownFields = true)
@Getter
@Setter
public class ShortenerProperties {

    private String noDataFound;
    private String invalidURL;
    private String linkExpired;
    private String expiresDate;
}
