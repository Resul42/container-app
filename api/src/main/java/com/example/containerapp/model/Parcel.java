package com.example.containerapp.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Parcel {

    private String id = UUID.randomUUID().toString();
    @JacksonXmlProperty(localName = "receipient")
    private Receipient receipient;
    @JacksonXmlProperty(localName = "weight")
    private Double weight;
    @JacksonXmlProperty(localName = "value")
    private Double value;
    private boolean handled;
    private boolean signedByInsurance;

    @Data
    public static class Receipient {
        @JacksonXmlProperty(localName = "name")
        private String name;
        @JacksonXmlElementWrapper(localName = "address")
        private Address address;
    }
}
