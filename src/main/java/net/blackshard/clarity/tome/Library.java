package net.blackshard.clarity.tome;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Library is a static wrapper around the Hibernate lifecycle.  It manages the
 * SessionFactory, and doles out Sessions.
 *
 * @author Matthew R. Trower
 * @version 1.0
 */
public class Library {
    private static final Logger log = LogManager.getLogger(Library.class);
    private static SessionFactory sessionFactory;

    /**
     * Opens the SessionFactory. This should be done exactly once, during
     * application initialization.
     *
     * @since 1.0
     */
    public static boolean open() {
        if (sessionFactory != null) {
            log.warn("failed opening Library: already open");
            return true;
        }

        ServiceRegistry registry = new ServiceRegistryBuilder()
            .loadProperties("hibernate.properties")
            .buildServiceRegistry();

        try {
            sessionFactory = new Configuration()
                .addPackage("net.blackshard.clarity.tome")
                .addAnnotatedClass(CPUReading.class)
                .addAnnotatedClass(MemReading.class)
                .buildSessionFactory(registry);
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            ServiceRegistryBuilder.destroy( registry );

            log.fatal("failed opening Library!");
            log.fatal(e.getStackTrace());

            return false;
        }

        return true;
    }

    /**
     * Opens and returns a session.
     *
     * @since 1.0
     */
    public static Session getBook() {
        return sessionFactory.openSession();
    }

    /**
     * Closes the SessionFactory.  Should be called exactly once, on application
     * cleanup.
     *
     * @since 1.0
     */
    public static void close() {
        if ( sessionFactory != null )
            sessionFactory.close();
    }
}
