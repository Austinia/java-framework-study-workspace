package com.austinia.usermanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 하나의 스프링 빈이 된다.
@RequestMapping("/api") // 요청 URL 매핑
@RequiredArgsConstructor // final에 대한 객체에 생명력을 준다.
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/list") // GET 요청 URL 매핑
    public List<User> list() {
        // List<User> users = userRepository.findAll(); // userRepository라는 곳에서 모든 데이터를 가져오면 좋겠어
        // return users; 리팩토링 - 변수 인라인화를 통해 리팩토링
        return userRepository.findAll();
    }
}
