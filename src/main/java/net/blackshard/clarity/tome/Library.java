package net.blackshard.clarity.tome;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Matthew R. Trower
 * class Library
 * 
 * Provide a static wrapper to manage Hibernate lifecycle
 */
public class Library {
    private static final Logger log = LogManager.getLogger(Library.class);
    private static SessionFactory sessionFactory;

    public static boolean open() {
        log.entry();
        // We only set up sessionFactory once!
        if (sessionFactory != null) {
            log.warn("failed opening Library: already open");
            return true;
        }

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

            log.fatal("failed opening Library!");
            log.fatal(e.getStackTrace());

            return false;
        }

        return true;
    }

    public static Session getBook() {
        return sessionFactory.openSession();
    }

    public static void close() {
        log.entry();

        if ( sessionFactory != null )
            sessionFactory.close();
    }
}
