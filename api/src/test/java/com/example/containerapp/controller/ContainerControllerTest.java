package com.example.containerapp.controller;

import com.example.containerapp.repository.ContainerRepository;
import com.example.containerapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.example.containerapp.util.ContainerHelper.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class ContainerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContainerRepository containerRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(authorities = "USER")
    void testRetrieveContainer() throws Exception {
        when(containerRepository.findAll()).thenReturn(List.of(container()));
        mockMvc.perform(get("/api/containers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("12345")));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"})
    void testRetrieveParcelMailDepartment() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userMail()));
        mockMvc.perform(get("/api/containers/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("2345")))
                .andExpect(jsonPath("$[0].value", is(1.0)))
                .andExpect(jsonPath("$[0].weight", is(0.1)))
                .andExpect(jsonPath("$[0].handled", is(false)))
                .andExpect(jsonPath("$[0].receipient.name", is("test user mail")))
                .andExpect(jsonPath("$[0].receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$[0].receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$[0].receipient.address.street", is("newstreet")));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testRetrieveParcelsRegularDepartment() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userRegular()));
        mockMvc.perform(get("/api/containers/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("3456")))
                .andExpect(jsonPath("$[0].value", is(100.0)))
                .andExpect(jsonPath("$[0].weight", is(5.0)))
                .andExpect(jsonPath("$[0].handled", is(false)))
                .andExpect(jsonPath("$[0].receipient.name", is("test user regular")))
                .andExpect(jsonPath("$[0].receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$[0].receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$[0].receipient.address.street", is("newstreet")));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testRetrieveParcelsHeavyDepartment() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userHeavy()));
        mockMvc.perform(get("/api/containers/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("4567")))
                .andExpect(jsonPath("$[0].value", is(250.0)))
                .andExpect(jsonPath("$[0].weight", is(150.0)))
                .andExpect(jsonPath("$[0].handled", is(false)))
                .andExpect(jsonPath("$[0].receipient.name", is("test user heavy")))
                .andExpect(jsonPath("$[0].receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$[0].receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$[0].receipient.address.street", is("newstreet")))
                .andExpect(jsonPath("$[1].id", is("6789")))
                .andExpect(jsonPath("$[1].value", is(1300.0)))
                .andExpect(jsonPath("$[1].weight", is(170.0)))
                .andExpect(jsonPath("$[1].handled", is(false)))
                .andExpect(jsonPath("$[1].signedByInsurance", is(true)))
                .andExpect(jsonPath("$[1].receipient.name", is("test user heavy")))
                .andExpect(jsonPath("$[1].receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$[1].receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$[1].receipient.address.street", is("newstreet")));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testRetrieveParcelsInsurance() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userInsurance()));
        mockMvc.perform(get("/api/containers/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("5678")))
                .andExpect(jsonPath("$[0].value", is(1250.0)))
                .andExpect(jsonPath("$[0].weight", is(150.0)))
                .andExpect(jsonPath("$[0].handled", is(false)))
                .andExpect(jsonPath("$[0].signedByInsurance", is(false)))
                .andExpect(jsonPath("$[0].receipient.name", is("test user heavy")))
                .andExpect(jsonPath("$[0].receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$[0].receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$[0].receipient.address.street", is("newstreet")))
                .andExpect(jsonPath("$[1].id", is("6789")))
                .andExpect(jsonPath("$[1].value", is(1300.0)))
                .andExpect(jsonPath("$[1].weight", is(170.0)))
                .andExpect(jsonPath("$[1].handled", is(false)))
                .andExpect(jsonPath("$[1].signedByInsurance", is(true)))
                .andExpect(jsonPath("$[1].receipient.name", is("test user heavy")))
                .andExpect(jsonPath("$[1].receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$[1].receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$[1].receipient.address.street", is("newstreet")));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testHandleMailParcel() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userMail()));
        mockMvc.perform(post("/api/containers/12345/parcel/2345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("2345")))
                .andExpect(jsonPath("$.value", is(1.0)))
                .andExpect(jsonPath("$.weight", is(0.1)))
                .andExpect(jsonPath("$.handled", is(true)))
                .andExpect(jsonPath("$.receipient.name", is("test user mail")))
                .andExpect(jsonPath("$.receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$.receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$.receipient.address.street", is("newstreet")));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testHandleRegularParcel() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userRegular()));
        mockMvc.perform(post("/api/containers/12345/parcel/3456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("3456")))
                .andExpect(jsonPath("$.value", is(100.0)))
                .andExpect(jsonPath("$.weight", is(5.0)))
                .andExpect(jsonPath("$.handled", is(true)))
                .andExpect(jsonPath("$.receipient.name", is("test user regular")))
                .andExpect(jsonPath("$.receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$.receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$.receipient.address.street", is("newstreet")));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testHandleHeavyParcel() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userHeavy()));
        mockMvc.perform(post("/api/containers/12345/parcel/4567"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("4567")))
                .andExpect(jsonPath("$.value", is(250.0)))
                .andExpect(jsonPath("$.weight", is(150.0)))
                .andExpect(jsonPath("$.handled", is(true)))
                .andExpect(jsonPath("$.signedByInsurance", is(false)))
                .andExpect(jsonPath("$.receipient.name", is("test user heavy")))
                .andExpect(jsonPath("$.receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$.receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$.receipient.address.street", is("newstreet")));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testSignInsurance() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userInsurance()));
        mockMvc.perform(post("/api/containers/12345/parcel/5678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("5678")))
                .andExpect(jsonPath("$.value", is(1250.0)))
                .andExpect(jsonPath("$.weight", is(150.0)))
                .andExpect(jsonPath("$.handled", is(false)))
                .andExpect(jsonPath("$.signedByInsurance", is(true)))
                .andExpect(jsonPath("$.receipient.name", is("test user heavy")))
                .andExpect(jsonPath("$.receipient.address.houseNumber", is(12)))
                .andExpect(jsonPath("$.receipient.address.postalCode", is("1122FF")))
                .andExpect(jsonPath("$.receipient.address.street", is("newstreet")));
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testHandleParcelFromWrongDepartment() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userRegular()));
        mockMvc.perform(post("/api/containers/12345/parcel/2345"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testHandleHeavyParcelNotSignedYet() throws Exception {
        when(containerRepository.findById("12345")).thenReturn(Optional.of(container()));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userHeavy()));
        mockMvc.perform(post("/api/containers/12345/parcel/5678"))
                .andExpect(status().isConflict());
    }
}
