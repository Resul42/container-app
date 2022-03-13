package com.example.containerapp.controller;

import com.example.containerapp.administration.request.user.AddUserRequest;
import com.example.containerapp.administration.request.user.ChangeUserRequest;
import com.example.containerapp.model.User;
import com.example.containerapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{uuid}")
    public User retrieve(@PathVariable("uuid") String uuid) {
        return userService.retrieve(uuid);
    }

    @GetMapping()
    public List<User> retrieveAll() {
        return userService.retrieveAll();
    }

    @PostMapping
    public User create(@Valid @RequestBody AddUserRequest userRequest) {
        return userService.create(userRequest);
    }

    @PutMapping
    public User update(@Valid @RequestBody ChangeUserRequest userRequest) {
        return userService.update(userRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        userService.delete(uuid);
    }

}
