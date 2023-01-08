package com.austinia.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "select id, name, password from userinfo where id = ?";
        // jdbcTemplate.query(sql, params, RowCallbackHandler(ResultSet))
        // ResultSet을 파라미터로 갖는 RowCallbackHandler를 구현해서 데이터를 매핑할 수 있다.
        return jdbcTemplate.query(sql, params, rs -> { // 람다식
            // 이전에 했던 방식 그대로
            User user = null;
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        });
    }

    public void insert(User user) {
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        String sql = "INSERT into userinfo (name, password) values (?, ?)";
        // jdbcTemplate.update(PreparedStatementCreator psc, KeyHolder keyHolder);
        // PreparedStatementCreater를 구현해서 키홀더에 id 값을 받아오자
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 생성된 id 값을 가져오기 위한 키홀더
        jdbcTemplate.update(con -> { // 람다식
            // 이전에 했던 방식 그대로
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) {
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        String sql = "UPDATE userinfo set name = ?, password = ? where id = ?";
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) {
        Object[] params = new Object[]{id};
        String sql = "DELETE from userinfo where id = ?";
        jdbcTemplate.update(sql, params);
    }
}
