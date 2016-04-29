package net.blackshard.clarity.tome;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/*
import org.hibernate.*;
import org.hibernate.cfg.*;
*/

/**
 * @author Matthew R. Trower
 * class Library
 * 
 * Provide a static wrapper to manage Hibernate lifecycle
 */
public class Library {
    private static SessionFactory sessionFactory;

    public static boolean open() {
        // We only set up sessionFactory once!
        if (sessionFactory != null) {
            System.out.println("Failed opening Library: already open");
            return true;
        }

        System.out.println("opening Library");

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .loadProperties("hibernate.properties")
            .build();

        try {
            sessionFactory = new MetadataSources( registry )
                .addPackage("net.blackshard.clarity.tome")
                .addAnnotatedClass(CPUReading.class)
                .buildMetadata()
                .buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );

            e.printStackTrace();
            System.out.println("Failed opening Library");

            return false;
        }

        System.out.println("Library open");
        return true;
    }

    public static Session getBook() {
        return sessionFactory.openSession();
    }

    public static void close() {
        System.out.println("closing library");

        if ( sessionFactory != null )
            sessionFactory.close();
        System.out.println("library closed");
    }
}
