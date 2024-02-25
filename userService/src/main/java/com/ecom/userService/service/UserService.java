package com.ecom.userService.service;

import com.ecom.userService.dto.OrderEvent;
import com.ecom.userService.dto.UserDTO;
import com.ecom.userService.entity.UserEntity;
import com.ecom.userService.repository.UserRepository;
import com.ecom.userService.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserDetails(String userId) throws Exception {

        UserEntity userEntity = userRepository.findByEmailId(userId).orElseThrow(() -> new Exception("User not Found."));

        UserDTO response = new UserDTO();
        //response.setUserId(userId);
        response.setFirstName(userEntity.getFirstName());
        response.setLastName(userEntity.getLastName());
        response.setEmailId(userEntity.getEmailId());

        return response;
    }


    public String createUserAccount(UserDTO request) throws Exception {

        request = ValidationUtil.validateUserRequest(request);
        Optional<UserEntity> user = userRepository.findByEmailId(request.getEmailId());
        if (user.isPresent()) {
            throw new Exception("Account Already exist with " + request.getEmailId() + " email id.");
        }

        UserEntity newUser = new UserEntity();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmailId(request.getEmailId());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        userRepository.save(newUser);

        return "Account Successfully Created.";
    }

    public String updateUserAccount(UserDTO request) throws Exception {
        request = ValidationUtil.validateUserRequest(request);
        Optional<UserEntity> userOptional = userRepository.findByEmailId(request.getEmailId());

        if (userOptional.isEmpty()) {
            throw new Exception("Account with email " + request.getEmailId() + " does not exist.");
        }

        UserEntity user = userOptional.get();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        // If you want to update the password, encode the new password
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // Set the updated timestamp
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return "Account Successfully Updated.";
    }

    public String deleteUserAccount(String emailId) throws Exception{
        Optional<UserEntity> userOptional = userRepository.findByEmailId(emailId);

        if (userOptional.isEmpty()) {
            throw new Exception("Account with email " + emailId + " does not exist.");
        }

        // here we did soft delete
        userOptional.get().setIsActive(false);
        userRepository.save(userOptional.get());

        return "Account Successfully deleted.";
    }
}
