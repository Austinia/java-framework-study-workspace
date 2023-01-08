package com.austinia.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class UserDaoTests {

    private static UserDao userDao;

    @BeforeAll
    public static void setup() throws ClassNotFoundException {
        // ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        StaticApplicationContext applicationContext = new StaticApplicationContext();

        BeanDefinition dataSourcebeanDefinition = new RootBeanDefinition(SimpleDriverDataSource.class);
        dataSourcebeanDefinition.getPropertyValues().addPropertyValue("driverClass"
                , Class.forName(System.getenv("DB_CLASSNAME")));
        dataSourcebeanDefinition.getPropertyValues().addPropertyValue("url"
                , System.getenv("DB_URL"));
        dataSourcebeanDefinition.getPropertyValues().addPropertyValue("username"
                , System.getenv("DB_USERNAME"));
        dataSourcebeanDefinition.getPropertyValues().addPropertyValue("password"
                , System.getenv("DB_PASSWORD"));
        applicationContext.registerBeanDefinition("dataSource", dataSourcebeanDefinition);

        BeanDefinition jdbcContextbeanDefinition = new RootBeanDefinition(JdbcTemplate.class);
        jdbcContextbeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("dataSource"));
        applicationContext.registerBeanDefinition("jdbcContext", jdbcContextbeanDefinition);

        BeanDefinition beanDefinition = new RootBeanDefinition(UserDao.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new RuntimeBeanReference("jdbcContext"));
        applicationContext.registerBeanDefinition("userDao", beanDefinition);

        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "Updated Hulk";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
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
