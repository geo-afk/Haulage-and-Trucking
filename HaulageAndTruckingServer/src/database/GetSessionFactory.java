package src.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.File;

public class GetSessionFactory {
    
    private static final Logger logger = LogManager.getLogger(GetSessionFactory.class);

    private GetSessionFactory() {}

    private static SessionFactory factory = null;

    public static SessionFactory buildSessionFactory(Class<?> cls) {
        Configuration config = new Configuration();

		File configFile = new File("HaulageAndTruckingServer\\resources\\hibernate.cfg.xml");
		logger.info("loaded configuration file... and activating session factory");

        try {
            if (factory == null) {
                config.configure(configFile);
                config.addAnnotatedClass(cls);
                factory = config.buildSessionFactory();
            }

            return factory;
        } catch (RuntimeException e) {
            logger.error("Error occurred while building session factory: " + e.getMessage(), e);
        }

        return null;
    }
}
