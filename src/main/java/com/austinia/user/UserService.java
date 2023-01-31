package com.austinia.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public List<UserDto> getAll() {
        return userDao.findAll();
    }

    public UserDto getById(Integer id) {
        return userDao.findById(id).orElseGet(() -> UserDto.builder().id(0).name("데이터 없음").password("존재하지 않음").build());
    }

    public UserDto upload(UserDto userDto) {
        userDao.save(userDto);
        return userDto;
    }

    public UserDto update(UserDto userDto) {
        UserDto tmpUserDto = userDao.findById(userDto.getId()).get();
        tmpUserDto.setName(userDto.getName());
        tmpUserDto.setPassword(userDto.getPassword());
        userDao.save(tmpUserDto);
        return tmpUserDto;
    }

    public void delete(Integer id) {
        userDao.deleteById(id);
    }
}
