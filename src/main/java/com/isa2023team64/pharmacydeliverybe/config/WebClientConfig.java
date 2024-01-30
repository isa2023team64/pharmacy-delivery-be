package com.isa2023team64.pharmacydeliverybe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }     
}
