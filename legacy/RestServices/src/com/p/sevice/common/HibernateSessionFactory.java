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
						// Configuration configuration = new Configuration()
						// .configure(FilePath);
						Configuration configuration = new Configuration();

						configuration.addAnnotatedClass (com.p.service.pojo.Topic.class);
						configuration.addAnnotatedClass (com.p.service.pojo.Group.class);
						configuration.addAnnotatedClass (com.p.service.pojo.View.class);
						configuration.addAnnotatedClass (com.p.service.pojo.TopicGroupRelation.class);
						configuration.addAnnotatedClass (com.p.service.pojo.ViewTopicRelation.class);
						configuration.addAnnotatedClass (com.p.service.pojo.GroupViewRelation.class);


						configuration.setProperty("hibernate.bytecode.use_reflection_optimizer","false");
						configuration.setProperty("hibernate.connection.characterEncoding", "UTF-8");    

						configuration.setProperty("connection.driver_class", "com.mysql.jdbc.Driver");   
						configuration.setProperty("connection.url", "jdbc:mysql://localhost:3306/topic_mgmt");  
						// configuration.setProperty("connection.url", "jdbc:mysql://host.docker.internal:3306/topic_mgmt");
						configuration.setProperty("connection.username", "root");
						configuration.setProperty("connection.password", "");
						configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");


						configuration.setProperty("format_sql","true");
						configuration.setProperty("connection.autoReconnect","true");
						configuration.setProperty("connection.autoReconnectForPools","true");
						configuration.setProperty("hibernate.search.autoregister_listeners","false");

						configuration.setProperty("show_sql","true");
						configuration.setProperty("hibernate.validator.apply_to_ddl","false");

						configuration.setProperty("current_session_context_class","thread");
						configuration.setProperty("hibernate.enable_lazy_load_no_trans","true");
						configuration.setProperty("hibernate.transaction.factory_class","org.hibernate.transaction.JDBCTransactionFactory");

						configuration.setProperty("connection.pool_size","0");
						configuration.setProperty("hibernate.c3p0.min_size","1");
						configuration.setProperty("hibernate.c3p0.max_size","20");
						configuration.setProperty("hibernate.c3p0.timeout","300");
						configuration.setProperty("hibernate.c3p0.max_statements","50");
						configuration.setProperty("hibernate.c3p0.idle_test_period","3000");






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
