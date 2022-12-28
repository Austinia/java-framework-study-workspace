package com.austinia.usermanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 빈으로 설정
public interface UserRepository extends JpaRepository<User, Integer> { // Jpa를 쓰면 저것을 확장한 인터페이스만 있으면 된다.
//    public List<User> findAll(); JpaRepsitory에 이미 있다.
}