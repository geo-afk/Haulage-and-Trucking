package entity;



public class Order {

    private Long invoiceNo;


    private Long customerId;


    //assumed to be made in office ( Kingston and Mo. Bay )
    private Double rate;
    private String driver;
    private String sourceAddress;
    private String destinationAddress;

  
    private Long billedBy; //Admin
}
