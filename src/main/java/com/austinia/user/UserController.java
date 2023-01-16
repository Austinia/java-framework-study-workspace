package com.austinia.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user") // RequestMethod"HandlerMapping"에 의해서 찾아진다
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @RequestMapping // RequestMapping"HandlerAdapter"에 의해서 찾아진다
    public User getUser(@RequestParam("id") Integer id) {
        return userDao.get(id);
    }
}
