package com.austinia.user;

import javax.servlet.*;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("**************** filter init ****************");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("**************** Filter before ****************");
        chain.doFilter(request, response); // 실제 서블릿이 실행하는 구간
        System.out.println("**************** Filter after ****************");
    }

    @Override
    public void destroy() {
        System.out.println("**************** filter destroy ****************");
    }
}
