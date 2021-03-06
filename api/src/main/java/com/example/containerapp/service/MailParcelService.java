package com.example.containerapp.service;

import com.example.containerapp.administration.exception.container.ContainerNotFoundException;
import com.example.containerapp.administration.exception.parcel.ParcelNotFoundException;
import com.example.containerapp.administration.exception.parcel.DepartmentAuthorizationException;
import com.example.containerapp.model.Parcel;
import com.example.containerapp.model.User;
import com.example.containerapp.repository.ContainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailParcelService implements ParcelService {

    private final ContainerRepository containerRepository;

    @Override
    public List<Parcel> retrieve(String containerId) {
        var container = containerRepository.findById(containerId)
                .orElseThrow(() -> ContainerNotFoundException.forId(containerId));

        return container.getParcels().stream()
                .filter(parcel -> parcel.getWeight() < 1)
                .collect(Collectors.toList());
    }

    @Override
    public Parcel handle(String containerId, String parcelId) {
        var container = containerRepository.findById(containerId)
                .orElseThrow(() -> ContainerNotFoundException.forId(containerId));

        var handledParcel = container.getParcels()
                .stream()
                .filter(parcel -> parcel.getId().equals(parcelId))
                .findAny()
                .orElseThrow(() -> ParcelNotFoundException.forId(parcelId));

        if (handledParcel.getWeight() >= 1) {
            throw DepartmentAuthorizationException.forDepartment(parcelId, User.Department.MAIL.toString());
        }
        handledParcel.setHandled(true);
        containerRepository.save(container);
        return handledParcel;
    }
}
