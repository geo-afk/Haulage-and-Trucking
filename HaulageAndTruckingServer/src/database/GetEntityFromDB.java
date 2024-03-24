package src.database;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import src.models.Trip;

import org.hibernate.Session;
import org.hibernate.Transaction;




public class GetEntityFromDB {

    private GetEntityFromDB() {}
    
    private static Session session;
    private static Transaction trans;
    
    public static Object getEntity(Integer idNum, Class<?> cls) {
        try {
            session = GetSessionFactory.buildSessionFactory(cls).openSession();
            trans = session.beginTransaction();

            Object obj = session.get(cls, idNum);

            if (obj == null) {
                throw new EntityNotFoundException("No data was found [" + cls.getSimpleName() + "]");
            }

            trans.commit();

            return obj;
        } catch (EntityNotFoundException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            GetSessionFactory.buildSessionFactory(cls).close();
        }

        return null;
    }

    
    public static List<Trip> getTripsByCriteria(Date minDate, Date maxDate, String driverId) {

       
        List<Trip> trips = null;

        try {

            session = GetSessionFactory.buildSessionFactory(Trip.class).openSession();
            trans = session.beginTransaction();


            Query<Trip> query = session.createQuery("FROM Trip WHERE driverId = :driverId AND date BETWEEN :minDate AND :maxDate", Trip.class);
            query.setParameter("driverId", driverId);
            query.setParameter("minDate", minDate);
            query.setParameter("maxDate", maxDate);

            trips = query.getResultList();
            trans.commit();
            
        
        } catch (EntityNotFoundException e) {

             if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
            
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            GetSessionFactory.buildSessionFactory(Trip.class).close();
        }

       return trips;
    }

    @SuppressWarnings("unchecked")
    public static List<Object> getEntitiesByCriteria(Class<?> cls) {

        List<Object> entities = null;

        try {

            session = GetSessionFactory.buildSessionFactory(cls).openSession();
            trans = session.beginTransaction();

            entities = (List<Object>) session.createQuery("FROM " + cls.getSimpleName(), cls).getResultList();

           
            trans.commit();

        } catch (RuntimeException e) {

             if (trans != null && trans.isActive()) {
                trans.rollback();
             }
             e.printStackTrace();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            GetSessionFactory.buildSessionFactory(cls).close();
        }

       return entities;
    }
    
}

