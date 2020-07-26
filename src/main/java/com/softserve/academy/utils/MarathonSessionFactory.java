package com.softserve.academy.utils;

import com.softserve.academy.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
public class MarathonSessionFactory {
    private static SessionFactory sessionFactory;

    private MarathonSessionFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("src/main/resources/hibernate.cfg.xml");

                configuration.addAnnotatedClass(Marathon.class);
                configuration.addAnnotatedClass(Progress.class);
                configuration.addAnnotatedClass(Sprint.class);
                configuration.addAnnotatedClass(Task.class);
                configuration.addAnnotatedClass(Users.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println(String.format("The exception was occur: %s", e));
            }
        }
        return sessionFactory;
    }
}
