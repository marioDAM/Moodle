package com.moodle.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class Security {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        System.out.println(SecurityContextHolder.getContext());
//        return http
//                //  .cors()
//                //.and()
//                .csrf()
//                .disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                //.authorizeRequests()
//                .authorizeHttpRequests()
//                .antMatchers("/**")
//                .permitAll()
//                // .antMatchers("/").permitAll()
//                // anyRequest().authenticated()
//                // .antMatchers("/admin").hasRole("ADMIN")
//                //.antMatchers("/**").permitAll()
//                //.antMatchers(HttpMethod.POST, "/addAdmin").permitAll()
//                .and()
//                .build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/images/", "/js/", "/webjars/");
//    }
//}