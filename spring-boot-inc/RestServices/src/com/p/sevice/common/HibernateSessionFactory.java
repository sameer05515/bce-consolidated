package com.p.sevice.common;



import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



// TODO: Auto-generated Javadoc
/**
 * Hibernate Session Factory Class.
 *
 * @author naveen
 */
public class HibernateSessionFactory {

	/** The session factory. */
	private static SessionFactory sessionFactory;
	
	/** The Constant FilePath. */
	private static final String FilePath = "com/p/service/dao/hibernate.cfg.xml";
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(HibernateSessionFactory.class.getName());

	/**
	 * Hibernate Session Factory Constructor.
	 */
	private HibernateSessionFactory() {

	}

	/**
	 * This method returns session factory object.
	 * 
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			// below changes are done for Thread Safe Singleton object Creation of Sessionfactory.
			synchronized (HibernateSessionFactory.class) {
				if (sessionFactory == null) {

					// loads configuration and mappings
					try {
						Configuration configuration = new Configuration()
						.configure(FilePath);
						StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties());
						sessionFactory = configuration.buildSessionFactory(builder
								.build());
						logger.info("Session Factory has been build up");
					} catch (Throwable ex) {
						// Make sure you log the exception, as it might be swallowed
						logger.info("Initial SessionFactory creation failed." + ex);
						throw new ExceptionInInitializerError(ex);
					}
				}
			}
		}
		return sessionFactory;
	}
	
	
	public static void main(String[] args) {
		System.out.println(HibernateSessionFactory.getSessionFactory());
	}
}
