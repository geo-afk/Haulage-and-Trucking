package model;

import java.io.Serializable;

public class CustomerBalance implements Serializable {

    private static final long serialVersionUID = 43232L;

    private Long id;

    private String companyName;

    private Route route;

    private double paid;

    private String driver;

    private String billedBy;

    public CustomerBalance() {

    }

    public CustomerBalance(String companyName, Route route, double paid, String driver, String billedBy) {
        this.companyName = companyName;
        this.route = route;
        this.paid = paid;
        this.driver = driver;
        this.billedBy = billedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
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

}
