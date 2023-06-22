// Filipe Nogueira Santos
//200534531
// MidTerm Exam - 23S Adv Object Oriented Prog-Java - 200 COMP1011-23S-31928
package com.example.f22midterm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtility {
    private static final String user = "student";
    private static final String password = "student";
    private static final String connString = "jdbc:mysql://localhost/F22Midterm";
    // database connnection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connString, user, password);
    }
}
