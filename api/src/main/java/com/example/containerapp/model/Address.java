package com.example.containerapp.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Address {
    @JacksonXmlProperty(localName = "street")
    private String street;
    @JacksonXmlProperty(localName = "houseNumber")
    private Integer houseNumber;
    @JacksonXmlProperty(localName = "postalCode")
    private String postalCode;
    @JacksonXmlProperty(localName = "city")
    private String city;
}

