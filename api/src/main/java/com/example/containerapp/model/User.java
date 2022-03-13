package com.example.containerapp.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String uuid;
    @Indexed(unique = true)
    private String email;
    private String password;
    private List<ApplicationRole> roles;
    private Department department;

    public enum Department {
        MAIL, REGULAR, HEAVY, INSURANCE
    }
    public enum ApplicationRole implements GrantedAuthority {
        ADMIN, USER;

        @Override
        public String getAuthority() {
            return this.toString();
        }
    }
}
