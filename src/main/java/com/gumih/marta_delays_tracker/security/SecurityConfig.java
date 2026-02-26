package com.gumih.marta_delays_tracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                // allow auth endpoints (register/login) without being logged in
                //.requestMatchers("/auth/**").permitAll()

                .requestMatchers("/", "/login", "/register", "/css/**", "/js/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                // allow stations to be read without login
                .requestMatchers(HttpMethod.GET, "/stations/**").permitAll()

                //.requestMatchers("/register").permitAll() //for Thymeleaf register functionality

                // everything else requires login
                .anyRequest().authenticated()
        );

        // allow browser login (session)
        //http.formLogin(Customizer.withDefaults());

        http.formLogin(form -> form
                .loginPage("/login")          // testing the thymeleaf login page
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
        );

        //Testing the Thymeleaf logout page
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
        );

        // allow Postman Basic Auth
        //http.httpBasic(Customizer.withDefaults());

        // disable CSRF for now so POST/PUT/DELETE work easily in Postman
       // http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
