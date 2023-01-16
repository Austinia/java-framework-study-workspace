package com.austinia.user;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 수행 전
        System.out.println("**************** Interceptor preHandle ****************");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 수행 직후 ( ModelAndView 를 여기서도 처리 할 수 있다 )
        System.out.println("**************** Interceptor postHandle ****************");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 수행 완료 후 ( Exception 를 여기서도 처리 할 수 있다 )
        System.out.println("**************** Interceptor afterCompletion ****************");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
