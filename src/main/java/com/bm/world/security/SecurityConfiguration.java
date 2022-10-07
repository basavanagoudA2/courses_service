package com.bm.world.security;

import com.bm.world.security.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration @RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 private final UserDetailsService userDetailsService;
 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 }

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  http.csrf().disable();
  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  http.authorizeRequests().antMatchers("/login").permitAll();
  http.authorizeRequests().antMatchers("/api/user/**").hasAnyAuthority("ROLE_USER");
  http.authorizeRequests().anyRequest().authenticated();
  http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
 }
 @Bean
 @Override
 public AuthenticationManager authenticationManagerBean()throws Exception{
  return super.authenticationManagerBean();
 }
 @Bean
 public PasswordEncoder passwordEncoder(){
  return new BCryptPasswordEncoder();
 }
}
