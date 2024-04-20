package model;

import java.io.Serializable;

public class Route implements Serializable {

    private static final long serialVersionUID = 63232L;

    private Long id;

    private String source;

    private Address sourceAddress;
    private Address destinationAddress;

    private Double rate;

    // Constructor
    public Route() {
    }

    public Route(String source, Address sourceAddress, Address destinationAddress, Double rate) {
        this.source = source;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
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

    public Address getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(Address sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
