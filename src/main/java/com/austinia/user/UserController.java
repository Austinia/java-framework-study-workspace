package com.austinia.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public List<UserDto> get() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public UserDto get(@PathVariable("id")Integer id) {
        return userService.findById(id);
    }
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping("/user/{id}")
    public UserDto update(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return userService.update(userDto, id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id")Integer id) {
        userService.delete(id);
    }

}
