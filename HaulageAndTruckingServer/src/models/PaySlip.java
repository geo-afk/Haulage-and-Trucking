package src.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pay_slip")
public class PaySlip implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long payId;


    @Column(name = "staff_id")
    private Staff staff;


    @Column(name = "start_date")
    private Date startDate;


    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "prepared_by")
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
