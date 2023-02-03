package com.austinia.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public boolean isValid(UserDto userDto) {
        if (userDao.findByName(userDto.getName()).isPresent()) {
            String dbPassword = userDao.findByName(userDto.getName()).get().getPassword();
            return passwordEncoder.matches(userDto.getPassword(), dbPassword);
        }
        return userDao.findByName(userDto.getName()).isPresent();
    }

    public String login(UserDto userDto) {
        if (isValid(userDto)) {
            return "OK";
        } else {
            throw new IllegalArgumentException("잘못된 아이디나 비밀번호 입니다");
        }
    }
}
