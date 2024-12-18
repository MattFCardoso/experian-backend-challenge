package com.experian.challenge.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Experian Backend Challenge API")
                        .description("API documentation for the Experian Backend Challenge application")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Developer")
                                .email("matheus.fcardoso@hotmail.com")
                                .url("https://www.donotexist.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .servers(List.of(new Server().url("http://localhost:8080").description("Local server")));
    }
}
