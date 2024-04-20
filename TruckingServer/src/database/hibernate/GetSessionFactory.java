package database.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetSessionFactory {

    private static final Logger logger = LogManager.getLogger(GetSessionFactory.class);

    private GetSessionFactory() {
    }

    private static SessionFactory factory = null;

    public static SessionFactory buildSessionFactory(Class<?> cls) {
        Configuration config = new Configuration();

        try {
            if (factory == null) {
                config.configure("hibernate.cfg.xml");
                logger.info("loaded configuration file... and activating session factory");
                config.addAnnotatedClass(cls);
                factory = config.buildSessionFactory();
            }

            return factory;
        } catch (RuntimeException e) {
            logger.error("Error occurred while building session factory: {}, {}", e.getMessage(), e);
        }

        return null;
    }
}
