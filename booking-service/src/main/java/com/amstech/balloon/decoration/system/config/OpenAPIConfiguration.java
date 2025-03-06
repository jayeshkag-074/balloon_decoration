package com.amstech.balloon.decoration.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {
    @Bean
	public OpenAPI defineOpenApi() {
		
    	Info info = new Info().title("balloon Decoration").version("1.0").description("this is unknown");
	return new OpenAPI().info(info);
    }
}
