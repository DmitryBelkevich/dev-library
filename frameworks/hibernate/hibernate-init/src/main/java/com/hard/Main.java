package com.hard;

import com.hard.models.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

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

class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static{
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/database1");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "1234");

        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");

        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        try{
            sessionFactory = new Configuration()
                    .configure()
//                .addClass(Category.class)
//                .setProperties(properties)
                    .buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
