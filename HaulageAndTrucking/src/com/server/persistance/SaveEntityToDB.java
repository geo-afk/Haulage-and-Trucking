package com.server.persistance;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaveEntityToDB {
    
    private static Session session;
    
    
    public static <T> void saveObject(T object) {
    	
        Transaction trans = null;
        
        try {
        	
        	
            session = GetSessionFactory.buildSessionFactory(object.getClass()).openSession();
            trans = session.beginTransaction();

            session.save(object);
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
        }
    }
}
