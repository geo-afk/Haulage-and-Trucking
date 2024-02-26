package com.server.persistance;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class GetEntityFromDB {
    
    private static Session session;
    private static Transaction trans;
    
    public static Object getEntity(Integer idNum, Class<?> cls) {
        try {
            session = GetSessionFactory.buildSessionFactory(cls).openSession();
            trans = session.beginTransaction();
            
            Object obj = session.get(cls, idNum);
            
            if (obj == null) {
                throw new RuntimeException("No data was found [" + cls.getSimpleName() + "]");
            }
            
            trans.commit();
            
            return obj;
        } catch (RuntimeException e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        return null;
    }
}
