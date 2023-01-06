package com.austinia.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMakeStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object obj, Connection connection) throws SQLException {
        User user = (User) obj; // user 형으로 다운 캐스팅
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE userinfo set name = ?, password = ? where id = ?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getId());
        return preparedStatement;
    }
}
