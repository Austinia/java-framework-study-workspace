package com.austinia.usermanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save") // POST 요청 URL 매핑
    public User create(@RequestBody User user) { // 스트링 자체를 오브젝트로 변환 시키는 RequestBody 사용
        return userRepository.save(user); // jpa에서 기본으로 제공
    }

    @GetMapping("/get/{id}") // parameter를 받아야 하니
    public User get(@PathVariable("id") Integer id) { // PathVariable id를 지정해준다.
        return userRepository.findById(id).get(); // id로 찾아서 get한다. findById는 Optional이다.
    }
}
