package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class Dao {

    Connection connect;

    void connect() {
        try  {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "password");
        } catch (SQLException e) {
            System.out.println("Cannot connect to database");
        }
    }
    void close() {
        try {
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
