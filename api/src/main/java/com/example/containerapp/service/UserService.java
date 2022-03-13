package com.example.containerapp.service;

import com.example.containerapp.administration.exception.user.UserAlreadyExistsException;
import com.example.containerapp.administration.exception.user.UserDoesNotExistException;
import com.example.containerapp.administration.request.user.AddUserRequest;
import com.example.containerapp.administration.request.user.ChangeUserRequest;
import com.example.containerapp.model.User;
import com.example.containerapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User retrieve(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> UserDoesNotExistException.forUuid(uuid));
    }

    public User retrieveByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserDoesNotExistException.forEmail(email));
    }

    public List<User> retrieveAll() {
        return userRepository.findAll();
    }

    public User create(AddUserRequest addUserRequest) {
        if (userRepository.existsByEmail(addUserRequest.getEmail())) {
            throw UserAlreadyExistsException.forEmail(addUserRequest.getEmail());
        }

        var user = new User();
        user.setEmail(addUserRequest.getEmail());
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(addUserRequest.getPassword()));
        user.setRoles(addUserRequest.getRoles());
        return userRepository.save(user);
    }

    public User update(ChangeUserRequest changeUserRequest) {
        var user = userRepository.findByUuidAndEmail(changeUserRequest.getUuid(), changeUserRequest.getEmail())
                .orElseThrow(() -> UserDoesNotExistException.forUuidAndEmail(changeUserRequest.getUuid(), changeUserRequest.getEmail()));
        user.setPassword(changeUserRequest.getPassword());
        user.setRoles(changeUserRequest.getRoles());
        return userRepository.save(user);
    }

    public void delete(String uuid) {
        var user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> UserDoesNotExistException.forUuid(uuid));
        userRepository.delete(user);
    }

}
