package pt.ncastro.markettradeprocessor.util.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.Oracle8iDialect;
import org.hibernate.dialect.PostgreSQL81Dialect;
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.service.ServiceRegistry;



/**
 * Some utilities to help using Hibernate.
 * 
 * @author Nuno de Castro
 *
 */
public class HibernateUtil {

	private static final Logger log = Logger.getLogger(HibernateUtil.class.getName());

	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry;

	private static String BINARY_COLLATION;
	private static final String CONFIGURATION_FILE = "pt/ncastro/markettradeprocessor/util/hibernate/hibernate.cfg.xml";


	/**
	 * Configures the session factory for the given connection.
	 *
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	private static SessionFactory configureSessionFactory() {
		Session session = null;

		try {
			Configuration cfg = new Configuration();
			cfg.configure(CONFIGURATION_FILE);

			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);

			// get the database default collation
			session = getCurrentSessionWithActiveTransaction();
			if (session != null) {
				// get the database schema
				String url = cfg.getProperty("hibernate.connection.url");
				String schema = url.substring(url.lastIndexOf("/") + 1);

				Query query = null;

				// add different DB queries to get collation
				switch (getDatabaseDialect()) {
					case MYSQL:
						query =
								session.createSQLQuery("SELECT default_character_set_name FROM information_schema.SCHEMATA WHERE schema_name = '"
										+ schema
										+ "';");
						break;
					case ORACLE:
						throw new RuntimeException("This database dialect was not handled yet, cannot proceed!");

					case POSTGRESQL:
						throw new RuntimeException("This database dialect was not handled yet, cannot proceed!");

					case SQLSERVER:
						throw new RuntimeException("This database dialect was not handled yet, cannot proceed!");

					case UNKNOWN:
						throw new RuntimeException("Unknown database dialect, cannot proceed!");
				}

				BINARY_COLLATION = query.uniqueResult() + "_bin";
				session.getTransaction().commit();

				// add needed configurations for utf8 databases
				if (BINARY_COLLATION.toLowerCase().contains("utf8")) {
					cfg.setProperty("hibernate.connection.CharSet", "utf8");
					cfg.setProperty("hibernate.connection.characterEncoding", "utf8");
					cfg.setProperty("hibernate.connection.useUnicode", "true");
					serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
					sessionFactory = cfg.buildSessionFactory(serviceRegistry);
				}
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Initial SessionFactory creation failed.", e);

		} finally {
			if (session != null && session.isOpen() && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
		}
		return sessionFactory;
	}


	/**
	 * Gets the Hibernate session factory for this application.
	 * 
	 * @return
	 */
	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = configureSessionFactory();
		}
		return sessionFactory;
	}


	/**
	 * Gets the connection to the DB.
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = null;

		Configuration configuration = new Configuration();
		String url = configuration.getProperty("hibernate.connection.url");
		String user = configuration.getProperty("hibernate.connection.username");
		String password = configuration.getProperty("hibernate.connection.password");

		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url + "?user=" + user + "&password=" + password);
		connection.setAutoCommit(true);

		return connection;
	}


	/**
	 * Gets the current session and starts the transaction if it is not already
	 * started.
	 * 
	 * @param session
	 */
	public static Session getCurrentSessionWithActiveTransaction() {
		Session session = getSessionFactory().getCurrentSession();
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return session;
	}


	/**
	 * Returns which database dialect is being currently used.
	 *
	 * @return
	 */
	public static HibernateDialect getDatabaseDialect() {
		String dialect = new Configuration().getProperty("hibernate.dialect");
		try {
			Class<?> c = Class.forName(dialect);
			if (MySQLDialect.class.isAssignableFrom(c)) {
				return HibernateDialect.MYSQL;
			} else if (Oracle8iDialect.class.isAssignableFrom(c)) {
				return HibernateDialect.ORACLE;
			} else if (PostgreSQL81Dialect.class.isAssignableFrom(c)) {
				return HibernateDialect.POSTGRESQL;
			} else if (SQLServerDialect.class.isAssignableFrom(c)) {
				return HibernateDialect.SQLSERVER;
			} else {
				return HibernateDialect.UNKNOWN;
			}
		} catch (ClassNotFoundException e) {
			return HibernateDialect.UNKNOWN;
		}
	}

	/**
	 * This enumerates the handled database dialects to be used onto the
	 * application.
	 *
	 * @author Nuno de Castro
	 */
	public enum HibernateDialect {
		UNKNOWN,
		MYSQL,
		SQLSERVER,
		ORACLE,
		POSTGRESQL;
	}
}
