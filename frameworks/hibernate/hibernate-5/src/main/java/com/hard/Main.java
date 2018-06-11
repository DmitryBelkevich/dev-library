package com.hard;

import com.hard.models.Category;
import com.hard.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;

        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            // Check database version
            String sql = "SELECT version()";

            NativeQuery nativeQuery = session.createNativeQuery(sql);
            String result = (String) nativeQuery.getSingleResult();
            System.out.println(result);

            // save Entity into database
            Category category = new Category();
            category.setTitle("category1");

            session.save(category);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();
    }
}
