package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {


        userService.createUsersTable();
        userService.saveUser("Nikita", "Konovalov", (byte) 25);
        userService.saveUser("Sergey", "Konovalov", (byte) 18);
        userService.saveUser("Andrey", "Konovalov", (byte) 53);
        userService.saveUser("Valentina", "Konovalova", (byte) 49);
        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();
    }
}
