package src.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SaveEntityToDB {

    private SaveEntityToDB() {}
    
    private static Session session;

    private static Object obj;
    
    
    public static <T> void saveObject(T object, Class<?> cls) {

        Transaction trans = null;

        try {

            session = GetSessionFactory.buildSessionFactory(cls).openSession();
            trans = session.beginTransaction();

            session.save(object);
            trans.commit();
            obj = object;

        } catch (RuntimeException e) {

            if (trans != null && trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();

        }
    }
    
    public static void closeSession() {
        if (session != null && session.isOpen()) {
            session.close();
        }
            GetSessionFactory.buildSessionFactory(obj.getClass()).close();
    }
}
