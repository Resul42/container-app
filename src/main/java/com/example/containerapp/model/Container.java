package com.example.containerapp.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "container")
//@XmlAccessorType(XmlAccessType.PROPERTY)
public class Container {

    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "shippingDate")
    private LocalDate shippingDate;
    @XmlElement(name = "parcels")
    private List<Parcel> parcels;


    @Data
    public static class Parcel {
        private String receipient;
        private String name;
        private Address address;
        private Integer weight;
        private Integer value;

        @Data
        public static class Address {
            private String street;
            private Integer houseNumber;
            private String postalCode;
            private String city;
        }
    }
}
