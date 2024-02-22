public class Customer {
    private int customerId;
    private String companyName;
    private String contactPerson;

    private Address address;
    private String telephone;
    private String email;
    private String status;

    // Constructor

    public Customer(int customerId, String companyName, String contactPerson, Address address,
                    String telephone, String email, String status) {

        this.customerId = customerId;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.status = status;
    }


    // Getters

    public int getCustomerId() {
        return customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }


    // Setters


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Example Operations
    public void addCustomer() {
        // Implementation code for adding a customer to the database
    }

    public void updateCustomer() {
        // Implementation code for updating a customer's details
    }

    public void deleteCustomer() {
        // Implementation code for deleting a customer from the database
    }

    public Customer getCustomerDetails() {
        // Return the customer's details; in a real scenario, you would fetch this from the database
        return this;
    }
}
