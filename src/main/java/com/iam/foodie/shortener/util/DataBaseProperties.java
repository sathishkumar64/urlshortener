package com.iam.foodie.shortener.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.iam.foodie.shortener.database", ignoreUnknownFields = true)
@Getter
@Setter
public class DataBaseProperties {

	private String databaseName;
	
	private String databaseUrl;
}
