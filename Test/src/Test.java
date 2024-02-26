import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {
	
	
	private static Transaction trans;
	
	
	public static void main(String[] args){
		
		Session session = HibernateFactory
				.getSessionFactory(Player.class)
				.openSession();
		
		
//		Player player = new Player();
//		player.setName("Wilson");
//		player.setAge(32);
//		player.setTeamName("Mighty Lions");
		
		try {
			trans = session.beginTransaction();
//			session.save(player);
			Player player = session.get(Player.class, 4L);
			player.setAge(21);
			System.out.println(player);
			session.update(player);
			trans.commit();
		}catch(RuntimeException e) {
			
			if(trans == null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		finally {
			
			if(trans == null) {
				 session.flush();
				session.close();
			}
			
		}
	}
}
