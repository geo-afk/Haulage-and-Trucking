public class TripOrder {
    private int invoiceNumber;
    private int customerId;
    private String sourceAddress;
    private String destinationAddress;
    private double rate;
    private int driverId;
    private String billedBy;

    // Constructor
    public TripOrder(int invoiceNumber, int customerId, String sourceAddress, String destinationAddress, double rate, int driverId, String billedBy) {
        this.invoiceNumber = invoiceNumber;
        this.customerId = customerId;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.rate = rate;
        this.driverId = driverId;
        this.billedBy = billedBy;
    }

    // Getters
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public double getRate() {
        return rate;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getBilledBy() {
        return billedBy;
    }

    // Setters
    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setBilledBy(String billedBy) {
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

    public TripOrder getTripOrderDetails() {
        // Return the trip/order details; in a real scenario, you would fetch this from the database
        return this;
    }
}
