package com.austinia.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor // Lombok에서 활용하는 유틸 중에 하나로 final로 정의된 오브젝트는 생성자를 자동으로 만들어 준다
public class SimpleController implements Controller {
    // 실제 비즈니스 로직을 req와 res 기반으로 잘 처리하고 view에다가 데이터를 넘기는 로직이 담긴 곳

    // DI 완료
    private final UserDao userDao;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // request의 parameter를 사용하는데 그것은 String이므로 Integer.valueOf()를 사용하자
        User user = userDao.findById(Integer.valueOf(request.getParameter("id"))).get();
        // Spring에서 제공하는 Model을 담는 그릇이자 View를 정의하는 그릇이다
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
