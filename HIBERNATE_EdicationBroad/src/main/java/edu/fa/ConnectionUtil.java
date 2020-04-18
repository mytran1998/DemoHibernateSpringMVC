package edu.fa;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConnectionUtil {
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			Configuration config = new Configuration();
			config.configure();
			ServiceRegistry sRegistry = new StandardServiceRegistryBuilder()
					.applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(sRegistry);
		}
		return sessionFactory;
	}
}
