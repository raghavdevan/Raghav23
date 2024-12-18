package com.hmsapp.hmsapp.congif;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(

            HttpSecurity http

    ) throws Exception {

        //h(cd)2
        http.csrf().disable().cors().disable();

        //haap
        http.authorizeHttpRequests().anyRequest().permitAll();
        return http.build();



    }

}
