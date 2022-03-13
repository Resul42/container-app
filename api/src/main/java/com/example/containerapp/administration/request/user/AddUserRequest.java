package com.example.containerapp.administration.request.user;

import com.example.containerapp.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AddUserRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    @Size(min = 1)
    private List<User.ApplicationRole> roles;
    @NotBlank
    private User.Department department;
}
