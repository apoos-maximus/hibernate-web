package org.apoos.hibernate;

import org.apoos.model.Account;
import org.apoos.model.Branch;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration config = new Configuration().configure().addAnnotatedClass(Account.class).addAnnotatedClass(Branch.class);
            ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
            SessionFactory sf = config.buildSessionFactory(registry);
            return sf;
        }
        catch (Throwable ex){
            System.out.println("Initisl SessionFactory Creation failed : " + ex );
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public void shutdown(){
        getSessionFactory().close();
    }
}