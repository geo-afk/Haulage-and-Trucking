package model;

import java.io.Serializable;

public class Trip implements Serializable{
	
	
    private int invoiceNumber;
    

    private Customer customerId;
	
	
    private Address sourceAddress;
	
	
    private Address destinationAddress;
	

    private Rate rate;
	
	
    private Long driverId;
	

    private Staff billedBy;

    // Constructor


	public Trip(int invoiceNumber, Customer customerId, Address sourceAddress, Address destinationAddress, Rate rate,
			Long driverId, Staff billedBy) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.customerId = customerId;
		this.sourceAddress = sourceAddress;
		this.destinationAddress = destinationAddress;
		this.rate = rate;
		this.driverId = driverId;
		this.billedBy = billedBy;
	}
	


	


    public int getInvoiceNumber() {
		return invoiceNumber;
	}






	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}






	public Customer getCustomerId() {
		return customerId;
	}






	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
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






	public Rate getRate() {
		return rate;
	}






	public void setRate(Rate rate) {
		this.rate = rate;
	}






	public long getDriverId() {
		return driverId;
	}






	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}






	public Staff getBilledBy() {
		return billedBy;
	}






	public void setBilledBy(Staff billedBy) {
		this.billedBy = billedBy;
	}






	// Example Operations
    public void addTripOrder() {
        // Implementation code for adding a trip/order to the database
    }

    public void updateTripOrder() {
        // Implementation code for updating a trip/order details
    }

    public void deleteTripOrder() {
        // Implementation code for deleting a trip/order from the database
    }

    public Trip getTripOrderDetails() {
        // Return the trip/order details; in a real scenario, you would fetch this from the database
        return this;
    }
}
