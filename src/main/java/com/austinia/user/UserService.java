package com.austinia.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public boolean isNameEmpty(String name) {
        return userDao.findByName(name).isEmpty();
    }

    public UserDto create(UserDto userDto) {
        if (isNameEmpty(userDto.getName())) {
            userDto.setRole("USER");
            userDto.encodePassword(passwordEncoder);
            userDao.save(userDto);
            return userDto;
        } else {
            throw new EntityExistsException(String.format("%s already taken", userDto.getName()));
        }
    }

    public List<UserDto> findAll() {
        return userDao.findAll();
    }

    public UserDto findById(Integer id) {
        Optional<UserDto> user = userDao.findById(id);
        UserDto userDto;
        if (user.isPresent()) {
            userDto = user.get();
            return userDto;
        } else {
            throw new NoSuchElementException(String.format("%s is not found", id));
        }
    }

    public UserDto update(UserDto userDto, Integer id) {
        Optional<UserDto> user = userDao.findById(id);
        if (user.isPresent()) {
            if (isNameEmpty(userDto.getName())) {
                userDto.setRole(user.get().getRole());
                userDto.setId(user.get().getId());
                userDto.encodePassword(passwordEncoder);
                userDao.save(userDto);
                return userDto;
            } else {
                throw new EntityExistsException(String.format("%s already taken", userDto.getName()));
            }
        } else {
            throw new NoSuchElementException(String.format("%s is not found", userDto.getId()));
        }
    }

    public void delete(Integer id) {
        userDao.deleteById(id);
    }
}
