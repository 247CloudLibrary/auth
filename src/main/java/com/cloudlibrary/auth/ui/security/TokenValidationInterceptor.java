package com.cloudlibrary.auth.ui.security;

import com.cloudlibrary.auth.exception.CloudLibraryException;
import com.cloudlibrary.auth.exception.MessageType;
import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

public class TokenValidationInterceptor extends HandlerInterceptorAdapter {
    Environment env;

    public TokenValidationInterceptor(Environment env) {
        this.env = env;
    }

    private boolean isJwtValid(String jwt) {
        boolean returnValue = true;

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(env.getProperty("jwt.secret")).parseClaimsJws(jwt).getBody()
                    .getSubject();
        } catch (Exception e) {
            returnValue =false;
        }
        if(subject ==null || subject.isEmpty())
            returnValue = false;

        return returnValue;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception{

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            throw new CloudLibraryException(MessageType.UNAUTHORIZED);
        }

        String jwt = authorizationHeader.replace("Bearer", "").trim();
        if (!isJwtValid(jwt)) {
            throw new CloudLibraryException(MessageType.UNAUTHORIZED);
        }
        return super.preHandle(request, response, handler);
    }
}
