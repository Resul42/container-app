package com.example.containerapp.service;

import com.example.containerapp.administration.exception.parcel.DepartmentAuthorizationException;
import com.example.containerapp.model.Parcel;
import com.example.containerapp.repository.ContainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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
class MailParcelServiceTest {

    @InjectMocks
    private MailParcelService mailParcelService;
    @Mock
    private ContainerRepository containerRepository;

    @Test
    @DisplayName("Testing the retrieval of mail parcels")
    void TestRetrievalMailParcels() {
        Mockito.when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        var mailParcelList = mailParcelService.retrieve("12345");
        assertThat(mailParcelList).isEqualTo(List.of(parcelMail()));
    }

    @Test
    @DisplayName("Testing the handling of a mail parcel")
    void testHandleMailParcel() {
        Mockito.when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        var handledParcel = mailParcelService.handle("12345", "2345");
        assertThat(handledParcel.isHandled()).isTrue();
    }

    @Test
    @DisplayName("Testing the handling of a regular parcel with a user from the mail department")
    void testHandleParcelRegularWhenUserIsMailDepartment() {
        Mockito.when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        assertThrows(DepartmentAuthorizationException.class, () -> mailParcelService.handle("12345", "3456"));
    }
}
