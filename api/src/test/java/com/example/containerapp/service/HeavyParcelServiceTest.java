package com.example.containerapp.service;

import com.example.containerapp.administration.exception.parcel.DepartmentAuthorizationException;
import com.example.containerapp.administration.exception.parcel.ParcelNotSignedException;
import com.example.containerapp.repository.ContainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.containerapp.util.ContainerHelper.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class HeavyParcelServiceTest {

    @InjectMocks
    private HeavyParcelService heavyParcelService;
    @Mock
    private ContainerRepository containerRepository;


    @Test
    @DisplayName("Testing the retrieval of heavy parcels")
    void TestRetrievalHeavyParcels() {
        Mockito.when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        var heavyParcelList = heavyParcelService.retrieve("12345");
        assertThat(heavyParcelList).isEqualTo(List.of(parcelHeavy(), parcelHeavyInsuranceSigned()));
    }

    @Test
    @DisplayName("Testing the handling of a heavy parcel")
    void testHandleHeavyParcel() {
        Mockito.when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        var handledParcel = heavyParcelService.handle("12345", "4567");
        assertThat(handledParcel.isHandled()).isTrue();
    }

    @Test
    @DisplayName("Testing the handling of a regular parcel with a user from the heavy department")
    void testHandleParcelRegularWhenUserIsHeavyDepartment() {
        Mockito.when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        assertThrows(DepartmentAuthorizationException.class, () -> heavyParcelService.handle("12345", "3456"));
    }

    @Test
    @DisplayName("Testing the handling of a heavy parcel which value is over 1000 and is not signed by Insurance department")
    void testHandleHeavyParcelNotSignedByInsurance() {
        Mockito.when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        assertThrows(ParcelNotSignedException.class, () -> heavyParcelService.handle("12345", "5678"));
    }
}
