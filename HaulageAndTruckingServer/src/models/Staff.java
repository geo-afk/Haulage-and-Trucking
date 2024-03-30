package src.models;

import java.io.Serializable;
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
public class Staff implements Serializable{
	
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
    private String position;
    
    @Column(name = "status")
    private boolean status;

    // Constructor

    public Staff() {
	}




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
