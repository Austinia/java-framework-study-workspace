package com.austinia.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests().antMatchers("/api/user").permitAll();
//                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//                .antMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated();
        return httpSecurity.build();
    }
}
