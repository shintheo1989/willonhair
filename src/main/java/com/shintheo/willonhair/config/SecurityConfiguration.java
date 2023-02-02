package com.shintheo.willonhair.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
 
 private final JwtAuthenticationFilter jwtAuthFilter;
 private final AuthenticationProvider authenticationProvider;

 @Bean
 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

  http
  .csrf()
  .disable()
  .authorizeHttpRequests()
  .mvcMatchers(HttpMethod.POST, "/api/**") // TODO("Remove this. It's temporally used for testing purpose")
  .permitAll()
  .mvcMatchers(HttpMethod.GET, "/api/**") // TODO("Remove this. It's temporally used for testing purpose")
  .permitAll()
  .mvcMatchers(HttpMethod.DELETE, "/api/**") // TODO("Remove this. It's temporally used for testing purpose")
  .permitAll()
  .mvcMatchers(HttpMethod.PUT, "/api/**") // TODO("Remove this. It's temporally used for testing purpose")
  .permitAll()
  
  .mvcMatchers(HttpMethod.PUT, "/reset/password/**") // TODO("Remove this. It's temporally used for testing purpose")
  .permitAll()
  .mvcMatchers(HttpMethod.POST, "/api/v1/auth/**")
  .permitAll()
  .mvcMatchers(HttpMethod.GET, "/swagger-ui/**", "api-docs/**")
  .permitAll()
  .anyRequest()
  .authenticated()
  .and()
  .sessionManagement()
  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  .and()
  .authenticationProvider(authenticationProvider)
  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
  
  
  return http.build();
 }
}