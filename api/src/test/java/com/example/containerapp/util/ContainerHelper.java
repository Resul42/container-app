package com.example.containerapp.util;

import com.example.containerapp.model.Address;
import com.example.containerapp.model.Container;
import com.example.containerapp.model.Parcel;
import com.example.containerapp.model.User;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ContainerHelper {
    public static Container container() {
        var container = new Container();
        container.setId("12345");
        container.setParcels(List.of(parcelMail(), parcelRegular(), parcelHeavy(), parcelHeavyInsuranceNotSigned(), parcelHeavyInsuranceSigned()));
        return container;
    }

    public static Parcel parcelMail() {
        var parcel = new Parcel();
        var receipient = new Parcel.Receipient();
        var address = new Address();
        address.setCity("city");
        address.setHouseNumber(12);
        address.setPostalCode("1122FF");
        address.setStreet("newstreet");

        receipient.setName("test user mail");
        receipient.setAddress(address);

        parcel.setId("2345");
        parcel.setValue(1.0);
        parcel.setWeight(0.1);
        parcel.setReceipient(receipient);
        return parcel;
    }

    public static Parcel parcelRegular() {
        var parcel = new Parcel();
        var receipient = new Parcel.Receipient();
        var address = new Address();
        address.setCity("city");
        address.setHouseNumber(12);
        address.setPostalCode("1122FF");
        address.setStreet("newstreet");

        receipient.setName("test user regular");
        receipient.setAddress(address);

        parcel.setId("3456");
        parcel.setValue(100.0);
        parcel.setWeight(5.0);
        parcel.setReceipient(receipient);
        return parcel;
    }

    public static Parcel parcelHeavy() {
        var parcel = new Parcel();
        var receipient = new Parcel.Receipient();
        var address = new Address();
        address.setCity("city");
        address.setHouseNumber(12);
        address.setPostalCode("1122FF");
        address.setStreet("newstreet");

        receipient.setName("test user heavy");
        receipient.setAddress(address);

        parcel.setId("4567");
        parcel.setValue(250.0);
        parcel.setWeight(150.0);
        parcel.setReceipient(receipient);
        return parcel;
    }

    public static Parcel parcelHeavyInsuranceNotSigned() {
        var parcel = new Parcel();
        var receipient = new Parcel.Receipient();
        var address = new Address();
        address.setCity("city");
        address.setHouseNumber(12);
        address.setPostalCode("1122FF");
        address.setStreet("newstreet");

        receipient.setName("test user heavy");
        receipient.setAddress(address);

        parcel.setId("5678");
        parcel.setValue(1250.0);
        parcel.setWeight(150.0);
        parcel.setReceipient(receipient);
        return parcel;
    }

    public static Parcel parcelHeavyInsuranceSigned() {
        var parcel = new Parcel();
        var receipient = new Parcel.Receipient();
        var address = new Address();
        address.setCity("city");
        address.setHouseNumber(12);
        address.setPostalCode("1122FF");
        address.setStreet("newstreet");

        receipient.setName("test user heavy");
        receipient.setAddress(address);

        parcel.setId("6789");
        parcel.setValue(1300.0);
        parcel.setWeight(170.0);
        parcel.setReceipient(receipient);
        parcel.setSignedByInsurance(true);
        return parcel;
    }

    public static User userRegular() {
        var user = new User();
        user.setDepartment(User.Department.REGULAR);
        return user;
    }

    public static User userMail() {
        var user = new User();
        user.setDepartment(User.Department.MAIL);
        return user;
    }

    public static User userHeavy() {
        var user = new User();
        user.setDepartment(User.Department.HEAVY);
        return user;
    }

    public static User userInsurance() {
        var user = new User();
        user.setDepartment(User.Department.INSURANCE);
        return user;
    }
}
