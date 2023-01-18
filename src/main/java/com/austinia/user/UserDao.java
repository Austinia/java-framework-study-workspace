package com.austinia.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Object, PrimaryKey>
public interface UserDao extends JpaRepository<User, Integer> {
    // Query 가 없이 사용하거나, Query 를 담을 수 도 있다
    // public List<User> findAllByName(String name);
}
