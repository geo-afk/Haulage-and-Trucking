package model;

import java.io.Serializable;

public class Route implements Serializable{
	
	
    private Long id;
	
	
    private String source;

    private Address sourceAddress;
    private Address destinationAddress;
	

    private Rate rateId; // This assumes rates are managed separately and linked to routes

    // Constructor
	public Route() {
	}

    public Route(String source, Address sourceAddress, Address destinationAddress, Rate rateId) {
        this.source = source;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.rateId = rateId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Address getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(Address sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Rate getRateId() {
        return rateId;
    }

    public void setRateId(Rate rateId) {
        this.rateId = rateId;
    }

    @Override
    public String toString() {
        return "Route [source=" + source + ", sourceAddress=" + sourceAddress + ", destinationAddress="
                + destinationAddress + ", rateId=" + rateId + "]";
    }
	
	
    
    
       
}
