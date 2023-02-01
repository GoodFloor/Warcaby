package pl.tp.server;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.spi.ServiceException;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // Configuration con = new Configuration().configure();
        // sessionFactory = con.buildSessionFactory();
        // return sessionFactory;
        try {
            if (sessionFactory == null) {
        
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
                Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
}
