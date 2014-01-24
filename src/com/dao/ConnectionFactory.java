package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
        String driverClassName = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://liquidbenchmark.net:3306/liquid";
        //String connectionUrl = "jdbc:mysql://localhost:3306/liquid";
        String dbUser = "liquid";
        String dbPwd = "liquid123";

        private static ConnectionFactory connectionFactory = null;

        private ConnectionFactory() {
                try {
                        Class.forName(driverClassName);
                } catch (ClassNotFoundException e) {
                	System.out.println(e.getMessage());
                        e.printStackTrace();
                }
        }

        public Connection getConnection() throws SQLException {
                Connection conn = null;
                conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
                return conn;
        }

        public static ConnectionFactory getInstance() {
                if (connectionFactory == null) {
                        connectionFactory = new ConnectionFactory();
                }
                return connectionFactory;
        }
}
