package model;

import java.io.Serializable;

public class Rate implements Serializable{
	
	
    private Long id;
	
	
    private String description;
	

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
