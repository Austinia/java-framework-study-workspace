package com.austinia.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public List<UserDto> get() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDto get(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/user")
    public UserDto upload(@RequestBody UserDto userDto) {
        return userService.upload(userDto);
    }

    @PutMapping("/user")
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

}
