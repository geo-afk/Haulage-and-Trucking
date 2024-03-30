package src.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "source")
    private String source;
	
	@OneToOne
	@JoinColumn(name = "destinationAddress")
    private Address destinationAddress;

    @OneToOne
	@JoinColumn(name = "sourceAddress")
    private Address sourceAddress;
	
	@OneToOne
	@JoinColumn(name = "rate_id")
    private Rate rateId; // This assumes rates are managed separately and linked to routes

    // Constructor
    public Route() {
        
	}

    public Route(String source, Address destinationAddress, Address sourceAddress, Rate rateId) {
        this.source = source;
        this.destinationAddress = destinationAddress;
        this.sourceAddress = sourceAddress;
        this.rateId = rateId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Address getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(Address sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Rate getRateId() {
        return rateId;
    }

    public void setRateId(Rate rateId) {
        this.rateId = rateId;
    }

    @Override
    public String toString() {
        return "Route [source=" + source + ", destinationAddress=" + destinationAddress + ", sourceAddress="
                + sourceAddress + ", rateId=" + rateId + "]";
    }
	



    
    
    
	
}
