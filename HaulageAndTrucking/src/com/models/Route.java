package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "source")
    private String source;
	
	@OneToOne
	@JoinColumn(name = "address_id")
    private Address destination;
	
	@OneToOne
	@JoinColumn(name = "rate_id")
    private Rate rateId; // This assumes rates are managed separately and linked to routes

    // Constructor
	public Route() {// TODO Auto-generated constructor stub
	}
	
	
    public Route(Long routeId, String source, Address destination, Rate rateId) {
        this.id = routeId;
        this.source = source;
        this.destination = destination;
        this.rateId = rateId;
    }

    // Getters
    public Long getRouteId() {
        return this.id;
    }

    public String getSource() {
        return source;
    }

    public Address getDestination() {
        return destination;
    }

    public Rate getRateId() {
        return rateId;
    }

    // Setters
    public void setRouteId(Long routeId) {
        this.id = routeId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public void setRateId(Rate rateId) {
        this.rateId = rateId;
    }

    // Example Operations
    public void addRoute() {
        // Implementation code for adding a route to the database
    }

    public void updateRoute() {
        // Implementation code for updating route details
    }

    public void deleteRoute() {
        // Implementation code for deleting a route from the database
    }

    public Route getRouteDetails() {
        // Return the route details; in a real scenario, you would fetch this from the database
        return this;
    }
}
