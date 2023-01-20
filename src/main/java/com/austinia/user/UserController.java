package com.austinia.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserDao userDao;

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        return userDao.findById(id).get();
    }

    @PostMapping("/upload")
    public User upload(User user) {
        userDao.save(user);
        return user;
    }

    @PutMapping("/update")
    public void update(@RequestBody User user) {
        User tmpuser = userDao.findById(user.getId()).get();
        tmpuser.setName(user.getName());
        tmpuser.setPassword(user.getPassword());
        userDao.save(tmpuser);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userDao.deleteById(id);
    }
}
