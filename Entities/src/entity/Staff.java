package entity;

import java.util.Date;

import constants.EmployeeType;
import constants.Status;

public class Staff {

 
    private Long staffId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address1;
    private String address2;
    private String parish;
    private String telephone;
    private String email;
    private EmployeeType position;
    private Status status;
}
