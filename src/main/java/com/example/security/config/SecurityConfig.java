package com.example.security.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        https.csrf(csrf -> {
            try {
                csrf .disable().sessionManagement(session -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS))
                        .authorizeHttpRequests(auth -> {
//                            auth.requestMatchers( "/{id}" ).hasRole( "USER" );
//                            auth.requestMatchers( "/all" ).hasRole( "ADMIN" );
                            auth.requestMatchers( "/api/v1/auth/**" ).permitAll();
                            auth.anyRequest()
                                    .authenticated();


                        });
            } catch (Exception e) {
                throw new RuntimeException( e );
            }
        } );

        https.authenticationProvider(authenticationProvider)
                .addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return https.build();
//        https.authorizeRequests()
//                .requestMatchers("/api/v1/auth/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return https.build();
    }

}
