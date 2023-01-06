package com.austinia.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            // driver, connection
            connection = dataSource.getConnection();
            // query
            StatementStrategy statementStrategy = new GetStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);
            // execute
            resultSet = preparedStatement.executeQuery();
            // result mapping
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            // close
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        // return
        return user;
    }

    public void insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // driver, connection
            connection = dataSource.getConnection();
            // query
            StatementStrategy statementStrategy = new InsertStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);
            // execute
            preparedStatement.executeUpdate();
            // mapping
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } finally {
            // close
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // driver, connection
            connection = dataSource.getConnection();
            // query
            StatementStrategy statementStrategy = new UpdateMakeStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);
            // execute
            preparedStatement.executeUpdate();
        } finally {
            // close
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // driver, connection
            connection = dataSource.getConnection();
            // query
            StatementStrategy statementStrategy = new DeleteStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);
            // execute
            preparedStatement.executeUpdate();
        } finally {
            // close
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
