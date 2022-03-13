package com.example.containerapp.service;

import com.example.containerapp.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.containerapp.model.User.Department.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParcelServices {

    private final MailParcelService mailParcelService;
    private final RegularParcelService regularParcelService;
    private final HeavyParcelService heavyParcelService;
    private final InsuranceParcelService insuranceParcelService;
    private final UserService userService;

    public ParcelService resolveParcelService(String email) {
        return parcelServiceForDepartment(resolveDepartment(email));
    }


    private User.Department resolveDepartment(String email) {
        return userService.retrieveByEmail(email).getDepartment();
    }


    ParcelService parcelServiceForDepartment(User.Department department) {
        if (MAIL.equals(department)) return mailParcelService;
        if (REGULAR.equals(department)) return regularParcelService;
        if (HEAVY.equals(department)) return heavyParcelService;
        if (INSURANCE.equals(department)) return insuranceParcelService;
        throw new IllegalStateException("No ParcelService implementation for department: " + department);
    }
}
