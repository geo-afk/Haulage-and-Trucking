package com.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
	
	
	
	public static void main(String[] args) {
		Session session = Factory.getSessionFactory().openSession();
		Transaction trans = null;
		
		
		try {
			
			trans = session.beginTransaction();
			Song song  =
					new Song();
			song.setArtistName("Jessie");
			song.setSongName("Bells and Wissles");
			
			session.save(song);
			session.get(Song.class, 1);
			trans.commit();
		
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			if(trans == null) {
				trans.rollback();
			}
			
		}
		finally {
			
			if(trans == null) {
				session.flush();
				session.close();
			}
		}
		
	}
	

}
