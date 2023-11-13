package com.isa2023team64.pharmacydeliverybe.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI openAPI() {
        ArrayList<Server> servers = new ArrayList<>(3);
        servers.add(new Server().url("http://localhost:8080/").description("development server"));
        servers.add(new Server().url("http://qa:8081/").description("test server"));
        servers.add(new Server().url("http://www.pharmacy-delivery.com/").description("production server"));

        Contact contact = new Contact()
                              .name("ISA 2023 Team 64")
                              .url("https://github.com/isa2023team64")
                              .email("nikolicveljko01@gmail.com");

        Info info = new Info()
                        .title("Pharmacy delivery")
                        .description("Pharmacy delivery API")
                        .version("v0.1")
                        .contact(contact);

        OpenAPI openAPI = new OpenAPI().info(info).servers(servers);

        return openAPI;
    }
}
