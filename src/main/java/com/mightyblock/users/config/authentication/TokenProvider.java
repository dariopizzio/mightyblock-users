package com.mightyblock.users.config.authentication;

import com.mightyblock.users.dto.TokenDto;
import com.mightyblock.users.model.exceptions.PropertyNotFoundException;
import com.mightyblock.users.utils.UserConstants;
import com.mightyblock.users.utils.UtilsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Value("${conf.token.expiration}")
    private int expirationTime;

    @Value("${conf.token.secretkey}")
    private String secretKey;

    @Autowired
    UtilsService utils;

    /**
     * Function to obtain a token given a username
     *
     * @param username username authenticated
     * @return TokenDto generated token
     */
    public TokenDto getToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("userJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return new TokenDto(PREFIX + token,
                new Date().getTime()+expirationTime);
    }

    /**
     * Function to validate the token of a request
     *
     * @param request request to evaluate
     * @return TokenDto generated token
     */
    public Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    /**
     * This function validates if the request has an Authorization header with the prefix Bearer
     *
     * @param request request to evaluate
     * @return boolean result of the validation
     */
    public boolean tokenExists(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        return (authenticationHeader != null && authenticationHeader.startsWith(PREFIX));
    }
}
