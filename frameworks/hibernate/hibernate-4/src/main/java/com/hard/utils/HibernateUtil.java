package com.hard.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/database1");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "1234");

        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");

        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        try {
            sessionFactory = new Configuration()
                    .configure()
//                .addClass(Category.class)
//                .setProperties(properties)
                    .buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Session Factory could not be created." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
