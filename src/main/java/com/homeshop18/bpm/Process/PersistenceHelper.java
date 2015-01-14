package com.homeshop18.bpm.Process;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Girish.Yadav
 *
 */
public class PersistenceHelper {
	/*
	 * static final Configuration configuration = new
	 * Configuration().configure(); static final StandardServiceRegistry
	 * serviceRegistry = new
	 * StandardServiceRegistryBuilder().applySettings(configuration
	 * .getProperties()).build(); static final SessionFactory sessionFactory =
	 * configuration.configure().buildSessionFactory(serviceRegistry); public
	 * static final SessionFactory getSession(){ return sessionFactory; }
	 */
	static final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("com.homeshop18.casepanel");

	public static final EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
