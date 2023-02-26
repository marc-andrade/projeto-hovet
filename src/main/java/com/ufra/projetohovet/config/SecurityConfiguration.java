package com.ufra.projetohovet.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final Environment env;
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
      http.headers().frameOptions().disable();
    }

    http.csrf()
        .disable()
            .cors(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
                .mvcMatchers("/h2-console/**").permitAll()
                .mvcMatchers("/login/token/**").permitAll()
                .mvcMatchers("/users/**").hasRole("ADMIN")
//                .requestMatchers(antMatcher(HttpMethod.GET,"/jewels/**")).permitAll()
//                .requestMatchers(antMatcher("/jewels/**")).hasAnyRole("ADMIN","USER")
//                .requestMatchers(antMatcher(HttpMethod.GET,"/categories/**")).permitAll()
//                .requestMatchers(antMatcher("/categories/**")).hasAnyRole("ADMIN","USER")
                .anyRequest().permitAll());

        http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors().configurationSource(corsConfigurationSource());

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOriginPatterns(List.of("*"));
    corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
    corsConfig.setAllowCredentials(true);
    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);
    return source;
  }

}
