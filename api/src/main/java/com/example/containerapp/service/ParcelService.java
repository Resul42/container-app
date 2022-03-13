package com.example.containerapp.service;

import com.example.containerapp.model.Parcel;

import java.util.List;

public interface ParcelService {
    List<Parcel> retrieve(String containerId);

    Parcel handle(String containerId, String parcelId);
}
