import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
	
	private static Configuration config;
	private static SessionFactory factory = null;
	
	public static SessionFactory getSessionFactory(Class<?> obj) {
		
		
		try {
			config = new Configuration();
			config.configure("hibernate.cfg.xml");
			
			
			if(factory == null) {
				config.addAnnotatedClass(obj);
				factory = config.buildSessionFactory();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return factory;
	}

}
