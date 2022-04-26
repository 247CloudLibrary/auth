package com.cloudlibrary.auth.ui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    Environment env;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenValidationInterceptor(env))
                .addPathPatterns("/**")
                .excludePathPatterns("/v1/auth/signup")
                .excludePathPatterns("/v1/auth/findpw/{uid}")
                .excludePathPatterns("/v1/auth/findid")
                .excludePathPatterns("/v1/auth/signup");


    }
}
