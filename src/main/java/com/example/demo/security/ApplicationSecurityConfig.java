package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.ApplicationUserRoles.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
              .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/css/*", "/js/*", "index").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails anna = User.builder().username("anna").password(passwordEncoder.encode("password")).roles(STUDENT.name()).build(); // ROLE_STUDENT
        UserDetails linda = User.builder().username("linda").password(passwordEncoder.encode("password")).roles(ADMIN.name()).build(); // ROLE_ADMIN
        UserDetails tom = User.builder().username("tom").password(passwordEncoder.encode("password")).roles(ADMINTRAINEE.name()).build(); // ROLE_ADMINTRAINEE
        return new InMemoryUserDetailsManager(anna, linda, tom);
    }
}
