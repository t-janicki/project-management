package com.auth.security;

class SecurityConstant {

    static final String[] SWAGGER = {
            // -- swagger ui
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources/**",
            "/configuration/ui/**",
            "/configuration/security/**",
            "/swagger-ui.html",
            "/webjars/**"
    };

    static final String[] REACT_FILES = {
            "/",
            "/favicon.ico",
            "/error",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"
    };
}
