package com.austinia.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserDao userDao;

    @GetMapping("/{id}")
    public UserDto get(@PathVariable("id") Integer id) {
        return userDao.findById(id).get();
    }

    @PostMapping("/upload")
    public UserDto upload(@RequestBody UserDto userDto) {
        userDao.save(userDto);
        return userDto;
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto userDto) {
        UserDto tmpUserDto = userDao.findById(userDto.getId()).get();
        tmpUserDto.setName(userDto.getName());
        tmpUserDto.setPassword(userDto.getPassword());
        userDao.save(tmpUserDto);
        return tmpUserDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userDao.deleteById(id);
    }
}
