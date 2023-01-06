package com.austinia.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {

    public Connection getConnection() throws ClassNotFoundException, SQLException;
}