package com.example.containerapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "container")
@JsonPropertyOrder({"id"})
@Data
@Document(collection = "containers")
public class Container {

    @JacksonXmlProperty(localName = "id")
    @Id
    private String id;
    @JacksonXmlProperty(localName = "shippingDate")
    private String shippingDate; //Was working on making @XmlJavaTypeAdapter(LocalDateTimeAdapter.class) on XMLGregorianCalendar but had some issues with jsr310. Was going to comeback to this later.
    @JacksonXmlElementWrapper(localName = "parcels")
    @JsonIgnore
    private List<Parcel> parcels = new ArrayList<>();

}
