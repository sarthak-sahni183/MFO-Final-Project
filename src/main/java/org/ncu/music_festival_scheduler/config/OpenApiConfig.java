package org.ncu.music_festival_scheduler.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("🎵 Music Festival Event Scheduler API")
                        .version("1.0")
                        .description("API for managing artists, stages, and performance schedules.")
                        .contact(new Contact()
                                .name("Festival Tech Team")
                                .email("support@musicfest.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
