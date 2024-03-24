package src.database;


import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetSessionFactory {
	
	private GetSessionFactory() {}
	
	private static SessionFactory factory = null;
	
	
	public static SessionFactory buildSessionFactory(Class<?> cls) {
		Configuration config = new Configuration();

		File configFile = new File("HaulageAndTruckingServer\\resources\\hibernate.cfg.xml");
		
		try {
			if(factory == null) {
				config.configure(configFile);
				config.addAnnotatedClass(cls);
				factory = config.buildSessionFactory();	
			}
			
			return factory;
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
