package org.akov.ws.security;

import org.akov.ws.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfigurer {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilter securityFilter(){
        return new SecurityFilter();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.POST, "/generateToken", "/register").permitAll() // pour le generateToken pour toute les personnes ont accées
                .requestMatchers("/api/**").hasAnyAuthority("ADMIN", "USER") // seulement pour les ADMIN
                //.requestMatchers("/api/user/**").hasAnyAuthority("USER") // seulement pour les ADMIN
                .anyRequest().authenticated()
        )
                // avant chaque request on va executer ce filtre
                .addFilterAfter(securityFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
