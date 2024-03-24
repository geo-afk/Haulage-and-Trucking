package src.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UpdateEntityInDB {

	private UpdateEntityInDB() {}
	
	
	private static Session session;
	private static Transaction trans;
	
	// List View of personals to choose to update
	// In interface when performing an update on an object populate field 
	// With data, then when update button is clicked sends the object here
	
	public static <T> void updateEntity(T object, Class<?> cls) {
		
		

		try {
			session = GetSessionFactory.buildSessionFactory(cls)
					.openSession();
			
			trans = session.beginTransaction();
			
			session.update(object);
			
			trans.commit();
			
			
		}catch (RuntimeException e) {
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
		
	}

}
