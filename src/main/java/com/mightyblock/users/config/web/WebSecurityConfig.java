package com.mightyblock.users.config.web;

import com.mightyblock.users.config.filter.AuthorizationFilter;
import com.mightyblock.users.config.authentication.TokenProvider;
import com.mightyblock.users.utils.UserConstants;
import com.mightyblock.users.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    UtilsService utils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .addFilterBefore(new AuthorizationFilter(tokenProvider, utils), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, UserConstants.LOGIN_URL).permitAll()
                .antMatchers(utils.getProperty(UserConstants.FILTER_EXCLUDED_URLS, String[].class)).permitAll()
                .anyRequest().authenticated();
    }
}
