package com.ecom.userService.util;

import com.ecom.userService.dto.UserDTO;

public class ValidationUtil {

    public static UserDTO validateUserRequest(UserDTO request) throws Exception {
        if (request.getFirstName()== null || request.getFirstName().isEmpty()) {
            throw new Exception("first name cannot be empty.");
        } else if (request.getLastName() == null || request.getLastName().isEmpty()){
            throw new Exception("last name cannot be empty.");
        } else if (request.getEmailId() == null || request.getEmailId().isEmpty()) {
            throw new Exception("email id cannot be empty.");
        } else if(request.getPassword() == null || request.getPassword().isEmpty()){
            throw new Exception("password cannot be empty.");
        } else if (request.getPassword().length()<8) {
            throw new Exception("password length cannot be less than 8.");
        } else{
            return request;
        }
    }
}
