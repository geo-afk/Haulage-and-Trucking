package model;

import java.io.Serializable;

public class Address implements Serializable{
    private String address1;
	
	
    private String address2;

	
	private String postOffice;
	 
	
	private String parish;


	// Constructor

	

	


	public Address() {
		
	}

	public Address(String address1, String address2, String postOffice, String parish) {
		this.address1 = address1;
		this.address2 = address2;
		this.postOffice = postOffice;
		this.parish = parish;
	}
	

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	public String getParish() {
		return parish;
	}

	public void setParish(String parish) {
		this.parish = parish;
	}
	
	
	 
}
