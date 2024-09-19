package com.aula.projeto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails vUser = User
        .withUsername("tahaskel")
        .password(passwordEncoder().encode("123abc"))
        .roles("USER")
        .build();

        return new InMemoryUserDetailsManager(vUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity pHttp) throws Exception {
        pHttp
            .csrf(pCsrf -> pCsrf.disable()) //Cross Site Request Forgery
            .cors(Customizer.withDefaults())
            .sessionManagement(
                pSession -> pSession.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(
                pPath -> 
                    pPath  
                        .requestMatchers("/swagger-ui/**").permitAll()
                        // .requestMatchers(HttpMethod.DELETE, "/swagger-ui/**").permitAll() só esse tipo de método
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/funcionario/**").permitAll()
                        .anyRequest()
                        .authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        return pHttp.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
