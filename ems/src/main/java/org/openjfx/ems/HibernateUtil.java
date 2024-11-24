package org.openjfx.ems;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.openjfx.ems.Entity.Admin;
import org.openjfx.ems.Entity.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

    private HibernateUtil() {
        // Private constructor to prevent instantiation
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            try {
                Configuration configuration = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Admin.class);

                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(registry);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExceptionInInitializerError("SessionFactory initialization failed: " + e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
