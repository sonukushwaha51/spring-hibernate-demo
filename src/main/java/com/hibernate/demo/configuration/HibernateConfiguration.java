package com.hibernate.demo.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.hibernate.demo.entity.Employee;


public class HibernateConfiguration {

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();

                try (InputStream inputStream = HibernateConfiguration.class.getClassLoader()
                        .getResourceAsStream("application.properties")) {
                    if (inputStream == null) {
                        throw new IOException("Exception occurred while reading");
                    }
                    properties.load(inputStream);
                }
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Employee.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
