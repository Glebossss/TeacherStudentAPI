package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityHandler extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    //Security regulations
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login.html")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login.html")
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
    }

}
