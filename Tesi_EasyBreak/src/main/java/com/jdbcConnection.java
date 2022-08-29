package com;

import java.sql.*;

public class jdbcConnection {
    private static jdbcConnection instance=null;
    private static Connection conn = null;

    public jdbcConnection(){};
    public static jdbcConnection getInstance() {
        return (instance == null) ? (instance = new jdbcConnection()) : instance;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EasyBreak", "root","Parkour!238" );
        return conn;
    }



}
