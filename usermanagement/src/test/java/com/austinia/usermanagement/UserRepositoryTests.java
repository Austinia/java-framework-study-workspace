package com.austinia.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    String name = "Austin";
    String password = "1234";
    @Test
    public void findAll() {

//        Integer id = 1; 아이디는 자동 생성 되므로 제거
//        String name = "Austin"; 전역 변수로

        User user = User.builder().name(name).build();
        entityManager.persist(user);

        List<User> users = userRepository.findAll(); // findAll을 테스트 할 것.
        assertThat(users.get(0).getId(), greaterThan(0)); // 0보다 크다는 것만 검증
        assertThat(users.get(0).getName(), is(name));
    }

    @Test
    public void save() {
        User user = User.builder().name(name).password(password).build();
        userRepository.save(user);
        assertThat(user.getId(), greaterThan(0)); // 생성이 되었으면 0보다 클 것
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

}
