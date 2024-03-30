package model;

import java.util.Date;

public class PaySlip {

   
    private Long payId;

    private Staff staff;
    private Date startDate;


    private Date endDate;
    private Double salary;
    private String admin;




    public PaySlip() {

    }




    public PaySlip(Staff staff, Date startDate, Date endDate, Double salary, String admin) {
        this.staff = staff;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
        this.admin = admin;
    }




    public Long getPayId() {
        return payId;
    }




    public void setPayId(Long payId) {
        this.payId = payId;
    }




    public Staff getStaff() {
        return staff;
    }




    public void setStaff(Staff staff) {
        this.staff = staff;
    }




    public Date getStartDate() {
        return startDate;
    }




    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }




    public Date getEndDate() {
        return endDate;
    }




    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }




    public Double getSalary() {
        return salary;
    }




    public void setSalary(Double salary) {
        this.salary = salary;
    }




    public String getAdmin() {
        return admin;
    }




    public void setAdmin(String admin) {
        this.admin = admin;
    }
    

    



}
