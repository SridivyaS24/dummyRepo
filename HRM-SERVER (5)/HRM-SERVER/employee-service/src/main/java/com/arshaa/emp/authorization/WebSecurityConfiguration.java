//package com.arshaa.emp.authorization;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//       http.csrf().disable().authorizeRequests()
//        .antMatchers("/emp").permitAll()
//        .antMatchers(HttpMethod.POST,"/createNewPotentialEmployee").permitAll()
//        .antMatchers(HttpMethod.PUT, "/updateApprovStatus/*").permitAll()
//        .antMatchers(HttpMethod.POST,"/newuser/*").permitAll()
//        .antMatchers(HttpMethod.GET,"/waitingForApprovelStatus").permitAll()
//         .antMatchers(HttpMethod.GET,"/exploreCourse").permitAll()
//        .anyRequest().authenticated();
//    }
//}