package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static Connection connection = null;
    private static Util util = null;
    private static final String URL = "jdbc:mysql://localhost/mydbtest";
    private static final String USER = "root1";
    private static final String PASSWORD = "root1";

    private Util() {
        try {
            if ((connection == null) || (connection.isClosed())) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к БД");
        }
    }

    public static Util getUtil() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

    public static Connection getConnection() {
        return connection;
    }
}
