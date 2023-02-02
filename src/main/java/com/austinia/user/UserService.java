package com.austinia.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDto userDto = userDao.findByName(username);
//        if (userDto == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return User.builder()
//                .username(userDto.getName())
//                .password(userDto.getPassword())
//                .roles(userDto.getRole())
//                .build();
//    }

    public UserDto create(UserDto userDto) {
        userDto.setRole("USER");
        userDto.encodePassword(passwordEncoder);
        return userDao.save(userDto);
    }

    public List<UserDto> findAll() {
        List<UserDto> userList = userDao.findAll();
        for (UserDto userDto : userList) {
            userDto.setPassword("CENSORED");
        }
        return userList;
    }

    public UserDto findById(Integer id) {
        Optional<UserDto> user = userDao.findById(id);
        UserDto userDto;
        if (user.isPresent()) {
            userDto = user.get();
            userDto.setPassword("CENSORED");
            return userDto;
        } else {
            throw new NoSuchElementException(String.format("%s is not found",id));
        }
    }

    public UserDto update(UserDto userDto, Integer id) {
        Optional<UserDto> user = userDao.findById(id);
        if (user.isPresent()) {
            userDto.setRole(user.get().getRole());
            userDto.setId(user.get().getId());
            userDto.encodePassword(passwordEncoder);
            userDao.save(userDto);
            userDto.setPassword("CENSORED");
            return userDto;
        } else {
            throw new NoSuchElementException(String.format("%s is not found",userDto.getId()));
        }
    }

    public void delete(Integer id) {
        userDao.deleteById(id);
    }
}
