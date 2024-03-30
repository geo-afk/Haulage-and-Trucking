package model;

import java.io.Serializable;

public class Customer implements Serializable{
	

    private Long id;
    private String companyName;
    private String contactPerson;
    private Address address;
    private String telephone;
    private String email;
    private boolean status;


    // Constructor
    
    public Customer() {
		
	}
    
    


    public Customer(String companyName, String contactPerson, Address address, String telephone, String email,
            boolean status) {
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.status = status;
    }
    

    


	// Getters

    public Long getCustomerId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public boolean getStatus() {
        return status;
    }


    // Setters


    public void setCustomerId(Long customerId) {
        this.id = customerId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Example Operations
    public void addCustomer() {
        // Implementation code for adding a customer to the database
    }

    public void updateCustomer() {
        // Implementation code for updating a customer's details
    }

    public void deleteCustomer() {
        // Implementation code for deleting a customer from the database
    }

    public Customer getCustomerDetails() {
        // Return the customer's details; in a real scenario, you would fetch this from the database
        return this;
    }
}
