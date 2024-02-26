package com.models;

import com.models.constant.EmployeeType;
import  com.models.constant.Status;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "staff")
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "first_name")
    private String firstName;
	
	@Column(name = "last_name")
    private String lastName;
	
	@Column(name = "d_o_b")
    private Date dateOfBirth;
    
    @OneToOne
	@JoinColumn(name = "address_id")
    private Address address;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "position")
    private EmployeeType position;
    
    @Column(name = "status")
    private Status status;

    // Constructor

    public Staff(Long staffId, String firstName, String lastName, Date dateOfBirth,
                 Address address, String email,
                 EmployeeType position, Status status) {

        this.id = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.position = position;
        this.status = status;
    }


    // Getters

    public Long getStaffId() {
        return id;
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

    public EmployeeType getPosition() {
        return position;
    }

    public Status getStatus() {
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

    public void setPosition(EmployeeType position) {
        this.position = position;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Example Operations
    public void addStaff() {
        // Implementation code for adding a staff member to the database
    }

    public void updateStaff() {
        // Implementation code for updating a staff member's details
    }

    public void deleteStaff() {
        // Implementation code for deleting a staff member from the database
    }

    public Staff getStaffDetails() {
        // Return the staff member's details; in a real scenario, you would fetch this from the database
        return this;
    }
}
