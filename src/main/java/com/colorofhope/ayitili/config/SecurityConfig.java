package com.colorofhope.ayitili.config;

import com.colorofhope.ayitili.service.MongoDBUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

  @Autowired
  public void configureGlobal(
      AuthenticationManagerBuilder auth, DaoAuthenticationProvider daoAuthenticationProvider)
      throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider);
  }

  @Configuration
  @EnableWebSecurity
  @EnableGlobalMethodSecurity(prePostEnabled = true)
  @Order(1)
  public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
      http.antMatcher("/api/**")
          .httpBasic()
          .and()
          .authorizeRequests()
          .anyRequest()
          .authenticated()
          .and()
          .csrf()
          .disable();
    }
  }

  @Configuration
  @EnableWebSecurity
  @EnableGlobalMethodSecurity(prePostEnabled = true)
  public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http.formLogin()
          .loginPage("/login")
          .and()
          .rememberMe()
          .tokenValiditySeconds(3600)
          .key("remember")
          .and()
          .logout()
          .permitAll()
          .logoutSuccessUrl("/")
          .and()
          .exceptionHandling()
          .accessDeniedPage("/")
          .and()
          .authorizeRequests()
          .and()
          .csrf()
          .disable();
    }
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(
      PasswordEncoder passwordEncoder, MongoDBUserDetailsService mongoDBUserDetailsService) {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    daoAuthenticationProvider.setUserDetailsService(mongoDBUserDetailsService);
    return daoAuthenticationProvider;
  }
}
