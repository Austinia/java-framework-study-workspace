package com.austinia.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestController {
    private final UserDao userDao;

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        return userDao.get(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        userDao.insert(user);
        return user;
    }
}
