package com.mightyblock.users.config;

import com.mightyblock.users.config.authentication.TokenProvider;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TokenProviderTestConfiguration {
    @Bean
    @Primary
    public TokenProvider tokenProvider() {
        return Mockito.mock(TokenProvider.class);
    }
}