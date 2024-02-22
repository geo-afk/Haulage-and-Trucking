public class Route {
    private int routeId;
    private String source;
    private Address destination;
    private int rateId; // This assumes rates are managed separately and linked to routes

    // Constructor
    public Route(int routeId, String source, Address destination, int rateId) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.rateId = rateId;
    }

    // Getters
    public int getRouteId() {
        return routeId;
    }

    public String getSource() {
        return source;
    }

    public Address getDestination() {
        return destination;
    }

    public int getRateId() {
        return rateId;
    }

    // Setters
    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public void setRateId(int rateId) {
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
