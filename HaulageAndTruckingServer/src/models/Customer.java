package src.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "company_name")
    private String companyName;
	
	@Column(name = "contact_person")
    private String contactPerson;

    
    @OneToOne()
    @JoinColumn(name = "address_id")
    private Address address;
    
    @Column(name = "telephone")
    private String telephone;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "status")
    private Boolean status;

    // Constructor
    
    public Customer() {
		
	}
    
    


    public Customer(Long id, String companyName, String contactPerson, Address address, String telephone, String email,
			boolean status) {
		super();
		this.id = id;
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
