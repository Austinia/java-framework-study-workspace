package com.austinia.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object obj, Connection connection) throws SQLException {
        Integer id = (Integer) obj; // Integer로 다운 캐스팅
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
