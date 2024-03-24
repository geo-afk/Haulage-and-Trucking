package model;

import java.util.Date;

public class Trip {

    private String invoiceNo;
    private String staffID;
    private String company;
    private String sourceAddress;
    private String destinationAddress;
    private String route;
    private double rate;
    private String driver;
    private Date deliveryDate;
    private String payStatus;

    public Trip() {
        this.setInvoiceNo("");
        this.setStaffID("");
        this.setCompany("");
        this.setSourceAddress("");
        this.setDestinationAddress("");
        this.setRoute("");
        this.setRate(0.0);
        this.setDriver("");
        this.setDeliveryDate(null);
        this.setPayStatus("");
    }

    public Trip(String invoiceNo, String staffID, String company, String sourceAddress, String destinationAddress, String route, double rate, String driver, Date deliveryDate, String payStatus) {
        this.setInvoiceNo(invoiceNo);
        this.setStaffID(staffID);
        this.setCompany(company);
        this.setSourceAddress(sourceAddress);
        this.setDestinationAddress(destinationAddress);
        this.setRoute(route);
        this.setRate(rate);
        this.setDriver(driver);
        this.setDeliveryDate(deliveryDate);
        this.setPayStatus(payStatus);
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
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



