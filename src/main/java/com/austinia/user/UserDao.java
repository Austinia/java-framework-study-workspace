package com.austinia.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserDto, Integer> {
    Optional<UserDto> findByName(String name);
}
