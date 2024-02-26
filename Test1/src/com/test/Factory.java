package com.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {
	
	
	private static SessionFactory factory = null;
	
	
	public static SessionFactory getSessionFactory() {
		
		
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Song.class);
		
		
		try {
			
			if(factory == null) {
				
				factory = config.buildSessionFactory();
			}
			
			return factory;
		}catch(RuntimeException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
