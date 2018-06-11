package com.hard._01_hello_world;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Check database version
        String sql = "SELECT version()";

        NativeQuery nativeQuery = session.createNativeQuery(sql);
        String result = (String) nativeQuery.getSingleResult();
        System.out.println(result);

        session.getTransaction().commit();
        session.close();

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

                // Apply settings
                registryBuilder.applySettings(settings);

                // Create serviceRegistry
                serviceRegistry = registryBuilder.build();

                // Create MetadataSources
                MetadataSources metadataSources = new MetadataSources(serviceRegistry);

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
