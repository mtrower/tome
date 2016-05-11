package net.blackshard.clarity.tome;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
        // We only set up sessionFactory once!
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

    public static Session getBook() {
        return sessionFactory.openSession();
    }

    public static void close() {
        log.entry();

        if ( sessionFactory != null )
            sessionFactory.close();
    }
}
