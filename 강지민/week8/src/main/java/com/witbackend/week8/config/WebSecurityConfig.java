package com.witbackend.week8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .exceptionHandling()

                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                // swagger 401 issue
                .antMatchers("/").anonymous()
                .antMatchers("/swagger-ui-html").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/v2/**").anonymous()
                .antMatchers("/csrf").anonymous()

                .antMatchers("/memebers").permitAll()
                .antMatchers("/members/**").permitAll()

                .anyRequest().permitAll();

        return http.build();
    }
}
