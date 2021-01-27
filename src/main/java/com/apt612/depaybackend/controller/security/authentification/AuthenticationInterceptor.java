package com.apt612.depaybackend.controller.security.authentification;

import com.apt612.depaybackend.controller.security.annotations.Authenticated;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (noAuthenticationRequired(handler)) {
            return true;
        }
        String token = request.getHeader("token");
        if (token != null) {
            return TokenUtils.verifyAuthen(token);
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
            if (authenticated != null) {
                return false;
            }
        }
        return true;
    }
}
