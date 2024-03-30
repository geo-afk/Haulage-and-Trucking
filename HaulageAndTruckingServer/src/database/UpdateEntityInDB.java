package src.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateEntityInDB {

    private static final Logger logger = LogManager.getLogger(UpdateEntityInDB.class);

    private UpdateEntityInDB() {}

    private static Session session;
    private static Transaction trans;

	public static <T> void updateEntity(T object, Class<?> cls) {
		logger.info("updating Objects from database: " + object.getClass());
        try {
			
            session = GetSessionFactory.buildSessionFactory(cls).openSession();
            trans = session.beginTransaction();
            session.update(object);
            trans.commit();
        } catch (RuntimeException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            logger.error("Error occurred while updating entity: " + e.getMessage(), e);
            GetSessionFactory.buildSessionFactory(cls).close();
		} finally {
			logger.info("Closing sessions");
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
