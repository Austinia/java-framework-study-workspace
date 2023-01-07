package com.austinia.user;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        Object[] params = new Object[]{id};
        String sql = "select id, name, password from userinfo where id = ?";
        return jdbcContext.get(sql, params);
    }

    public void insert(User user) throws SQLException {
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        String sql = "INSERT into userinfo (name, password) values (?, ?)";
        jdbcContext.insert(sql, params, user);
    }

    public void update(User user) throws SQLException {
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        String sql = "UPDATE userinfo set name = ?, password = ? where id = ?";
        jdbcContext.update(sql, params);
    }

    public void delete(Integer id) throws SQLException {
        Object[] params = new Object[]{id};
        String sql = "DELETE from userinfo where id = ?";
        jdbcContext.update(sql, params);
    }
}
