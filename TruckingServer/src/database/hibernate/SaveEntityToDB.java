package database.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaveEntityToDB {

    private static final Logger logger = LogManager.getLogger(SaveEntityToDB.class);

    private SaveEntityToDB() {
    }

    private static Session session;

    public static <T> void saveObject(T object, Class<?> cls) {
        Transaction trans = null;

        logger.info("Saving Object to the database: {}", object.getClass());
        try {
            session = GetSessionFactory.buildSessionFactory(cls).openSession();
            trans = session.beginTransaction();

            session.save(object);
            trans.commit();
        } catch (RuntimeException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            logger.error("Error occurred while saving object: {}", e.getMessage(), e);
            GetSessionFactory.buildSessionFactory(cls).close();
        } finally {
            logger.info("Closing sessions");
            closeSession();
        }
    }

    public static void closeSession() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
