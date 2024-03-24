package src.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
	@Column(name = "address_1", nullable = false)
    private String address1;
	
	@Column(name = "address_2", nullable = false)
    private String address2;

	@Column(name = "post_office")
    private String postOffice;
	
	@Column(name = "parish")
	private String parish;
	


	public Address() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
