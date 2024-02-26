package com.server.persistance;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetSessionFactory {
	
	private static Configuration config;
	private static SessionFactory factory = null;
	
	
	public static SessionFactory buildSessionFactory(Class<?> cls) {
		
		config = new Configuration();
		
		try {
			if(factory == null) {
				config.configure("hibernate.cfg.xml");
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
