package com.example.containerapp.controller;

import com.example.containerapp.model.Container;
import com.example.containerapp.model.Parcel;
import com.example.containerapp.repository.ContainerRepository;
import com.example.containerapp.service.ParcelService;
import com.example.containerapp.service.ParcelServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
@RequiredArgsConstructor
@Slf4j
public class ContainerController {

    private final ParcelServices parcelServices;
    private final ContainerRepository containerRepository;

    @GetMapping()
    public List<Container> retrieve() {
        return containerRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Parcel> retrieveParcels(Authentication authentication, @PathVariable("id") String id) {
        var parcelService = parcelServices.resolveParcelService(authentication.getName());
        return parcelService.retrieve(id);
    }

    @PostMapping("/{id}/parcel/{parcelId}")
    public Parcel handleParcel(Authentication authentication, @PathVariable("id") String id, @PathVariable("parcelId") String parcelId) {
        var parcelService = parcelServices.resolveParcelService(authentication.getName());
        return parcelService.handle(id, parcelId);
    }
}
