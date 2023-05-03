package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {

    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;
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

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties property = new Properties();
            property.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            property.put(Environment.URL, URL);
            property.put(Environment.USER, USER);
            property.put(Environment.PASS, PASSWORD);
            property.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            property.put(Environment.SHOW_SQL, "True");
            property.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            property.put(Environment.HBM2DDL_AUTO, "create-drop");
            configuration.setProperties(property);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
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
