import constants.EmployeeType;
import constants.Status;

import java.util.Date;

public class Staff {
    private int staffId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Address address;
    private String email;
    private EmployeeType position;
    private Status status;

    // Constructor

    public Staff(int staffId, String firstName, String lastName, Date dateOfBirth,
                 Address address, String email,
                 EmployeeType position, Status status) {

        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.position = position;
        this.status = status;
    }


    // Getters

    public int getStaffId() {
        return staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeType getPosition() {
        return position;
    }

    public Status getStatus() {
        return status;
    }


    // Setters


    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(EmployeeType position) {
        this.position = position;
    }

    public void setStatus(Status status) {
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
