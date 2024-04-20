package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_balance")
public class CustomerBalance implements Serializable {

    private static final long serialVersionUID = 43232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @OneToOne
    @JoinColumn(name = "route")
    private Route route;

    @Column(name = "paid")
    private double paid;

    @Column(name = "driver_name")
    private String driver;

    @Column(name = "billed_by")
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
