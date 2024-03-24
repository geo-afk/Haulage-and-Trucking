package src.database;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteEntityFromDB {

	private DeleteEntityFromDB() {}
	
	private static Session session;
	
	
	public static <T> void deleteEntity(T object, Class<?> cls) {

		Transaction trans = null;

		try {

			session = GetSessionFactory.buildSessionFactory(cls)
					.openSession();

			trans = session.beginTransaction();

			session.delete(object);

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

	}
	


}
