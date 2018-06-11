package com.hard;

import com.hard.models.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
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
