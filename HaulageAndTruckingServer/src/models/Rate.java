package src.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rate")
public class Rate implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "value")
    private double value;

    // Constructor
	public Rate() {
		
	}
	
    public Rate(Long rateId, String description, double value) {
        this.id = rateId;
        this.description = description;
        this.value = value;
    }

    // Getters
    public Long getRateId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    // Setters
    public void setRateId(Long rateId) {
        this.id = rateId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // Example Operations
    public void addRate() {
        // Implementation code for adding a rate to the database
    }

    public void updateRate() {
        // Implementation code for updating rate details
    }

    public void deleteRate() {
        // Implementation code for deleting a rate from the database
    }

    public Rate getRateDetails() {
        // Return the rate details; in a real scenario, you would fetch this from the database
        return this;
    }
}
