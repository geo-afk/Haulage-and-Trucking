package model;

import java.io.Serializable;

public class Route implements Serializable{
	
	
    private Long id;
	
	
    private String source;
	

    private Address destination;
	

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
