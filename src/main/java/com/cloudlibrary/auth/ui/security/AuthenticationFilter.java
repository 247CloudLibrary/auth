package com.cloudlibrary.auth.ui.security;

import com.cloudlibrary.auth.application.service.AuthReadUseCase;
import com.cloudlibrary.auth.application.service.AuthService;
import com.cloudlibrary.auth.ui.requestBody.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthService authService;
    Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, AuthService authService,Environment env) {
        super.setAuthenticationManager(authenticationManager);
        super.setFilterProcessesUrl("/v1/auth/signin");
        this.authService = authService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        try {
            LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    creds.getUserId(), creds.getPassword(), new ArrayList<>());
            return getAuthenticationManager().authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException{
        String userName = ((User) authResult.getPrincipal()).getUsername();

        AuthReadUseCase.FindAuthResult findAuthResult = authService.getAuthById(userName);

        String token = Jwts.builder()
                .setSubject(findAuthResult.getUserId())
                .setExpiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(env.getProperty("jwt.token-validity-in-seconds"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("jwt.secret"))
                .compact();


        Cookie setCookie = new Cookie("token",token);
        response.addCookie(setCookie);
        response.addHeader("token",token);
        response.addHeader("authUid", Long.toString(findAuthResult.getUid()));
        response.addHeader("authId",findAuthResult.getUserId());
    }
}
