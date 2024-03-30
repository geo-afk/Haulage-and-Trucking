package src.models;

import java.io.Serializable;

public class TripReport implements Serializable{
    private long invoiceNumber;
    private String company;
    private String sourceAddress;
    private String destinationAddress;
    private double rate;
    private String driverName;
    private String billedBy;

    
    
    public TripReport() {

    }



    public TripReport(long invoiceNumber, String company, String sourceAddress, String destinationAddress, double rate,
            String driverName, String billedBy) {
        this.invoiceNumber = invoiceNumber;
        this.company = company;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.rate = rate;
        this.driverName = driverName;
        this.billedBy = billedBy;
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



    public double getRate() {
        return rate;
    }



    public void setRate(double rate) {
        this.rate = rate;
    }



    public String getDriverName() {
        return driverName;
    }



    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }



    public String getBilledBy() {
        return billedBy;
    }



    public void setBilledBy(String billedBy) {
        this.billedBy = billedBy;
    }



    @Override
    public String toString() {
        return "TripReport [invoiceNumber=" + invoiceNumber + ", company=" + company + ", sourceAddress="
                + sourceAddress + ", destinationAddress=" + destinationAddress + ", rate=" + rate + ", driverName="
                + driverName + ", billedBy=" + billedBy + "]";
    }
    
    
    
    
}