package model;

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
public class Route implements Serializable {

    private static final long serialVersionUID = 63232L;

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

    @Column(name = "rate")
    private Double rate; // This assumes rates are managed separately and linked to routes

    // Constructor
    public Route() {

    }

    public Route(String source, Address destinationAddress, Address sourceAddress, Double rate) {
        this.source = source;
        this.destinationAddress = destinationAddress;
        this.sourceAddress = sourceAddress;
        this.rate = rate;
    }

    public Route(Route obj) {
        this.source = obj.getSource();
        this.destinationAddress = obj.getDestinationAddress();
        this.sourceAddress = obj.getDestinationAddress();
        this.rate = obj.getRate();
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
