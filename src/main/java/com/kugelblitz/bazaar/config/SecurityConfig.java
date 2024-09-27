package com.kugelblitz.bazaar.config;

import com.kugelblitz.bazaar.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  private final CustomUserDetailsService userDetailsService;

  public SecurityConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  // Bean to create a BCryptPasswordEncoder for password encryption
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Bean to define the security filter chain
  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/login", "/register", "/registerUser", "/api/**")
                    .permitAll() // Allows access to the login and register pages to all users
                    .anyRequest()
                    .authenticated()) // Other requests need Authentication
        .formLogin(
            form ->
                form.loginPage("/login") // Custom login page
                    .defaultSuccessUrl("/home") // Redirect to home on successful authentication
                    .permitAll()) //
        .logout(LogoutConfigurer::permitAll);
    return http.build();
  }

  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
}
