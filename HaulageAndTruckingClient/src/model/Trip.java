package model;

import java.io.Serializable;
import java.util.Date;

public class Trip implements Serializable{
	
	
    private long invoiceNumber;
	
	private String company;
	

    private Route route;
	
	
    private String driver;
	

	private String billedBy;
	
	private Date deliveryDate;
	
    private String payStatus;

    // Constructor


	public Trip() {

	}

	public Trip(String company, Route route, String driver, String billedBy, Date deliveryDate, 
			String payStatus) {
		this.company = company;
		this.route = route;
		this.driver = driver;
		this.billedBy = billedBy;
		this.deliveryDate = deliveryDate;
		this.payStatus = payStatus;
	}

	public long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getBilledBy() {
		return billedBy;
	}

	public void setBilledBy(String billedBy) {
		this.billedBy = billedBy;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}




	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	


	

	


	   
}
