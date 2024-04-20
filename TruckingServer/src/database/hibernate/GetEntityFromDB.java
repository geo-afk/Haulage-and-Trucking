package database.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class GetEntityFromDB {

    private static final Logger logger = LogManager.getLogger(GetEntityFromDB.class);

    private GetEntityFromDB() {
    }

    private static Session session;
    private static Transaction trans;

    public static Object getEntity(Integer idNum, Class<?> cls) {
        try {
            session = GetSessionFactory.buildSessionFactory(cls).openSession();
            trans = session.beginTransaction();

            Object obj = session.get(cls, idNum);

            if (obj == null) {
                logger.info("No data was found [ {} ]", cls.getSimpleName());
                throw new EntityNotFoundException("No data was found [" + cls.getSimpleName() + "]");
            }

            trans.commit();

            return obj;

        } catch (EntityNotFoundException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            logger.error("Entity not found: {}", e.getMessage(), e);
            GetSessionFactory.buildSessionFactory(cls).close();
        } catch (RuntimeException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            logger.error("Error occurred while fetching entity: {}", e.getMessage(), e);
            GetSessionFactory.buildSessionFactory(cls).close();
        } finally {
            logger.info("Closing session");
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<?> getEntitiesByCriteria(Class<?> cls) {
        List<Object> entities = null;

        try {
            session = GetSessionFactory.buildSessionFactory(cls).openSession();
            trans = session.beginTransaction();

            logger.info("Hibernate Query: FROM {}", cls.getSimpleName());

            entities = (List<Object>) session.createQuery("FROM " + cls.getSimpleName(), cls).getResultList();

            trans.commit();
        } catch (RuntimeException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            logger.error("Error occurred while fetching entities by criteria: {}", e.getMessage(), e);
            GetSessionFactory.buildSessionFactory(cls).close();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entities;
    }
}
