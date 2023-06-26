package com.example.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            //this param is for extracting data from the request
            @NonNull HttpServletRequest request,
            //this param is for providing new data in the response
            @NonNull HttpServletResponse response,
            //this param contains a list of other filters that we need
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        //this string contains the JWT token
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String username;
        //checking the token
        if (authHeader == null || !authHeader.startsWith("bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        //extracting the jwt from the header
        //7 because it starts after "bearer "
        token = authHeader.substring(7);
        username = jwtService.extractUsername(token);
        //checks if the user exists and isn't authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //this gets the user details from the database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            //after checking the validity of the user we need to check the token validity
            if(jwtService.isTokenValid(token, userDetails)) {
                //this token is needed to update the security context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                //this enforces the details of the request on the token
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
