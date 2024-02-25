package com.ecom.userService.controller;

import com.ecom.userService.dto.AuthRequest;
import com.ecom.userService.dto.AuthResponse;
import com.ecom.userService.dto.UserDTO;
import com.ecom.userService.service.AuthenticationService;
import com.ecom.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody UserDTO request
    ) throws Exception {
        return ResponseEntity.ok(userService.createUserAccount(request));
    }
    @PostMapping("/get-token")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
