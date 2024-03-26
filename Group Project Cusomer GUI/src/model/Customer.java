package model;

public class Customer {
	private String customerId;
    private String companyName;
    private String contactPerson;

    private String address1;
    private String address2;
    private String postOffice;
    private String parish;
    private String telephone;
    private String email;
    private String status;
    
    //Default Constructor
    public Customer() {
    	this.setCustomerId("");
    	this.setCompanyName("");
    	this.setContactPerson("");
    	this.setAddress1("");
    	this.setAddress2("");
    	this.setPostOffice("");
    	this.setParish("");
    	this.setTelephone("");
    	this.setEmail("");
    	this.setStatus("");
    }
    
    //Primary Constructor
    public Customer(String customerId, String companyName, String contactPerson, String address1,String address2, String postOffice, String parish, String telephone,String email, String status) {
    	this.setCustomerId(customerId);
    	this.setCompanyName(companyName);
    	this.setContactPerson(contactPerson);
    	this.setAddress1(address1);
    	this.setAddress2(address2);
    	this.setPostOffice(postOffice);
    	this.setParish(parish);
    	this.setTelephone(telephone);
    	this.setEmail(email);
    	this.setStatus(status);
    }

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
