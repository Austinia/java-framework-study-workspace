package com.austinia.repository;

import com.austinia.domain.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserDto, Integer> {
    Optional<UserDto> findByName(String name);
}
