package model;

import java.io.Serializable;
import java.util.Date;

public class Staff implements Serializable{
	
    private Long id;
	
    private String firstName;
	

    private String lastName;
	
	
    private Date dateOfBirth;

   
    private Address address;
    
   
    private String email;
    
   
    private String position;
    

    private boolean status;

    // Constructor

    public Staff() {
        
    }
    



    // Getters

 
    




    public Long getStaffId() {
        return id;
    }

    public Staff(String firstName, String lastName, Date dateOfBirth, Address address, String email,
            String position, boolean status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.position = position;
        this.status = status;
    }




    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public boolean getStatus() {
        return status;
    }


    // Setters


    public void setStaffId(Long staffId) {
        this.id = staffId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
