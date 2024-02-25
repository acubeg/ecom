package com.ecom.userService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {
//    private String userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
}
