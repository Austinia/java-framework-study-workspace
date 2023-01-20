package com.austinia.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserDto, Integer> {
}
