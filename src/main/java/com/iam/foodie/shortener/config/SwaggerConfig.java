
package com.iam.foodie.shortener.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Autowired
	BuildProperties buildProperties;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new io.swagger.v3.oas.models.info.Info().description("URL Shortener API's ...")
						.title("URL Shortener Service -API's").version(buildProperties.getVersion()));

	}

	@Bean
	public GroupedOpenApi shortenerOpenApi() {
		String[] paths = { "/api/v1/**" };
		return GroupedOpenApi.builder().group("Shortener").pathsToMatch(paths).build();
	}


}
