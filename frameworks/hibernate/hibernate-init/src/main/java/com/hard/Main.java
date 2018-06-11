package com.hard;

import com.hard.models.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
//        Properties properties = new Properties();
//
//        properties.put(Environment.DRIVER, "org.postgresql.Driver");
//        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/database1");
//        properties.put(Environment.USER, "postgres");
//        properties.put(Environment.PASS, "1234");
//
//        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
//
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.hbm2ddl.auto", "update");

        SessionFactory sessionFactory = new Configuration()
                .configure()
//                .addClass(Category.class)
//                .setProperties(properties)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        // save Entity into database
        Category category = new Category();
        category.setTitle("category1");

        session.save(category);

        session.getTransaction().commit();

        session.close();
    }
}
