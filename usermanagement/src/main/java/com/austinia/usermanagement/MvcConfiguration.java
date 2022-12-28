package com.austinia.usermanagement;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 설정 파일
public class MvcConfiguration implements WebMvcConfigurer { // WebMvcConfigurer를 임플리먼트
    @Override // command + N 으로 메서드 구현을 통해 구현한 메서드로 뷰컨트롤러 레지스트리에게 /list의 경로로 뷰컨트롤러를 추가하겠다는 말이다.
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/list");
        registry.addViewController("/create").setViewName("edit"); // create.html이 없으므로 edit.html으로 설정해 준다.
        registry.addViewController("/edit");
    }

}
