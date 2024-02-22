public class Rate {
    private int rateId;
    private String description;
    private double value;

    // Constructor
    public Rate(int rateId, String description, double value) {
        this.rateId = rateId;
        this.description = description;
        this.value = value;
    }

    // Getters
    public int getRateId() {
        return rateId;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    // Setters
    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // Example Operations
    public void addRate() {
        // Implementation code for adding a rate to the database
    }

    public void updateRate() {
        // Implementation code for updating rate details
    }

    public void deleteRate() {
        // Implementation code for deleting a rate from the database
    }

    public Rate getRateDetails() {
        // Return the rate details; in a real scenario, you would fetch this from the database
        return this;
    }
}
