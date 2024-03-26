import java.util.Date;

public class Staff {
    private int staffId;
    private String name;
    private Date dateOfBirth;
    private String address;
    private String telephone;
    private String email;
    private String position;
    private String status;

    // Constructor
    public Staff(int staffId, String name, Date dateOfBirth, String address, String telephone, String email, String position, String status) {
        this.staffId = staffId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.position = position;
        this.status = status;
    }

    // Getters
    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Example Operations
    public void addStaff() {
        // Implementation code for adding a staff member to the database
    }

    public void updateStaff() {
        // Implementation code for updating a staff member's details
    }

    public void deleteStaff() {
        // Implementation code for deleting a staff member from the database
    }

    public Staff getStaffDetails() {
        // Return the staff member's details; in a real scenario, you would fetch this from the database
        return this;
    }
}
