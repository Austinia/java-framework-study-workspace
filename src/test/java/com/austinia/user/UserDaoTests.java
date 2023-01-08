package com.austinia.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class UserDaoTests {

    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        // 패키지 전체를 등록하게 되면 @Configuration과 @Bean 등의 어노테이션들을 찾아서 알아서 빈을 등록한다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.austinia.user");
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void testGet() {
        Integer id = 1;
        String name = "Updated Hulk";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() {
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
    public void update() {
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
    public void delete() {
        String name = "HaveToDeleteThis";
        String password = "666";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.get(user.getId());
        assertThat(deletedUser, IsNull.nullValue());
    }
}
