package com.hard;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

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

class HibernateUtil {
    private static StandardServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create serviceRegistry builder
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Map<String, String> settings = new HashMap<>();

                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/database1");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "1234");

                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");

                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "update");

                // Apply settings
                registryBuilder.applySettings(settings);

                // Create serviceRegistry
                serviceRegistry = registryBuilder.build();

                // Create MetadataSources
                MetadataSources metadataSources = new MetadataSources(serviceRegistry);
                metadataSources.addAnnotatedClass(Category.class);

                // Create Metadata
                MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
                Metadata metadata = metadataBuilder.build();

                // Create SessionFactory
                SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();

                sessionFactory = sessionFactoryBuilder.build();
            } catch (Exception e) {
                e.printStackTrace();
                if (serviceRegistry != null) {
                    StandardServiceRegistryBuilder.destroy(serviceRegistry);
                }
            }
        }

        return sessionFactory;
    }

    public static void shutdown() {
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}

@Entity
@Table(name = "categories")
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
