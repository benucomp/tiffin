package com.benu.code.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;    
    public static SessionFactory getSessionFactory() {
    	
        if (sessionFactory == null ) {       	
        	Configuration configuration = new Configuration();
            configuration.configure();		
    		ServiceRegistryBuilder servRegBuild = new ServiceRegistryBuilder();
    		servRegBuild.applySettings(configuration.getProperties());	
    		ServiceRegistry serviceRegistry = servRegBuild.buildServiceRegistry();
    		
    		sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);            
        }
        return sessionFactory;
    }
}