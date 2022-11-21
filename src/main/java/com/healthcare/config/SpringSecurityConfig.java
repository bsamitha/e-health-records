package com.healthcare.config;

import com.healthcare.model.Doctor;
import com.healthcare.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("sam").password("{noop}pass").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/patients/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/patients").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/patients/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/patients/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/doctors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/doctors").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/doctors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/doctors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/reports/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/reports").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/reports/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/reports/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
