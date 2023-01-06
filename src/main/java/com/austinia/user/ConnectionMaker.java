package com.austinia.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {

    public Connection getConnection() throws ClassNotFoundException, SQLException;
//        // driver
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        // connection
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jejunu", "jeju", "jeju");
//        return connection;

}