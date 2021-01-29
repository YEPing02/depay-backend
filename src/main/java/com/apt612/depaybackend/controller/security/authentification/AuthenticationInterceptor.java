package com.apt612.depaybackend.controller.security.authentification;

import com.apt612.depaybackend.controller.security.annotations.Authenticated;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (noAuthenticationRequired(handler)) {
            return true;
        }
        String token = request.getHeader("Token");
        if (token != null) {
         boolean valid=  TokenUtils.verifyAuth(token);
         if(valid){
             return true;
         }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    private boolean noAuthenticationRequired(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Authenticated authenticated = handlerMethod.getMethod().getAnnotation(Authenticated.class);
            if (authenticated == null) {
                authenticated = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Authenticated.class);
            }
            return authenticated == null;
        }
        return true;
    }
}
