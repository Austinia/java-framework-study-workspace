package com.austinia.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class UserDaoTests {

    private static UserDao userDao;

    @BeforeAll // 테스트 전에 수행해야할 것을 정의
    public static void setup() {
        // 빈들을 관리하는 여러 관리 방법 중에 하나.
        // 어노테이션을 이용해서 빈을 관리하는 전략 패턴.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        // 여기(DaoFactory.java)에 빈의 정의가 어노테이션으로 되어 있다.
        userDao = applicationContext.getBean("userDao", UserDao.class);
        // UserDao타입의 userDao라는 이름의 Bean을 주세요.
        // 이게 Dependency Lookup이라는 개념이다.
    }

    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "Updated Hulk";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(),is(id));
        assertThat(user.getName(),is(name));
        assertThat(user.getPassword(),is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "Austin";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        assertThat(user.getId(), greaterThan(0));
        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    @Test
    public void update() throws SQLException {
        String name = "BeforeUser";
        String password = "1234";
        String updatedName = "AfterName";
        String updatedPassword = "4321";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        user.setName(updatedName);
        user.setPassword(updatedPassword);
        userDao.update(user);

        User updatedUser = userDao.get(user.getId());
        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));
    }

    @Test
    public void delete() throws SQLException {
        String name = "HaveToDeleteThis";
        String password = "666";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.get(user.getId()); // 없는 걸 가져왔으니
        assertThat(deletedUser, IsNull.nullValue()); // null 인 것을 검증.
    }
}
