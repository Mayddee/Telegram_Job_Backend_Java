package org.example.jobsprojectbackend.Config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Telegram Jobs Parser API")
                        .description("REST API for managing companies and vacancies scraped from Telegram")
                        .version("1.0.0"));
    }
}