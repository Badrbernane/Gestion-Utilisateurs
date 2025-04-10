package org.bernard.myappjavafx.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author BERNANE
 **/
public class Configuration {
    private static final String URL = "jdbc:mysql://localhost:3306/pour_javaavncé";
    private static final String USER = "root";
    private static final String PASSWORD = "Badr";

    public static Connection GetConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL non trouvé", e);
        }
    }
}