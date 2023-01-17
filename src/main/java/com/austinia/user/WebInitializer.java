package com.austinia.user;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

// web.xml
public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // applicationContext 를 Annotation 기반의 빈등록 방식으로 한다
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        // 이 패키지 밑의 빈들을 스캔하자
        applicationContext.scan("com.austinia.user");
        // ServletContext 에 applicationContext 를 가진 DispatcherServlet 을 등록하고
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dispatcher"
                , new DispatcherServlet(applicationContext));
        // 우선 순위를 맨 처음으로 두고
        servletRegistration.setLoadOnStartup(1);
        // 전체 URL 에 Mapping 한다
        servletRegistration.addMapping("/");
    }
}
