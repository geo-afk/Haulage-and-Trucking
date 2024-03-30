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
@Table(name = "trip")
public class Trip implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long invoiceNumber;
    
	@Column(name = "company")
    private String company;

	
	
	@OneToOne
	@JoinColumn(name = "route_id")
    private Route route;
	
	@Column(name = "driver_name")
    private String driver;
	
	@Column(name = "billed_by")
	private String billedBy;
	

	@Column(name = "delivery_date")
	private Date deliveryDate;


	@Column(name = "pay_status")
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

	public void setInvoiceNumber(int invoiceNumber) {
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

	public void setDeliveryDate(Date startDate) {
		this.deliveryDate = startDate;
	}

	

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	


	



}
