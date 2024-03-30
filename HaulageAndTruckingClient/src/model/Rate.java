package model;

import java.io.Serializable;

public class Rate implements Serializable{
	
	
    private Long id;
	
	
    private String description;
	

    private double value;

    // Constructor
	public Rate() {
		
	}
	
    public Rate(String description, double value) {
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

    @Override
    public String toString() {
        return "Rate [description=" + description + ", value=" + value + "]";
    }



    
}
