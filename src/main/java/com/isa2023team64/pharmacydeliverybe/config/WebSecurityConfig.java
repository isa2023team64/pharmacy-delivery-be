package com.isa2023team64.pharmacydeliverybe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.isa2023team64.pharmacydeliverybe.config.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URL = {"/auth/**",
    "/v2/api-docs",
    "/v3/api-docs",
    "/v3/api-docs/**",
    "/swagger-resources",
    "/swagger-resources/**",
    "/configuration/ui",
    "/configuration/security",
    "/swagger-ui/**",
    "/webjars/**",
    "/*.html",
    "favicon.ico",
    "/*/*.html",
    "/*/*.css",
    "/*/*.js",
    "/ws/**", "/ws",
    "/api/registration/**",
    "/api/companies",
    "/api/equipment",
    "/api/equipment/{id}",
    "/api/companies/search",
    "/api/equipment/search",
    "/api/equipment/{search}/equipment",
    "/swagger-ui.html"};

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req ->
                req.requestMatchers(WHITE_LIST_URL)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
    
}
