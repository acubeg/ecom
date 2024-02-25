package com.ecom.userService.controller;

import com.ecom.userService.dto.UserDTO;
import com.ecom.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping
	public UserDTO getUserDetails(@RequestParam(name = "user-id", required = true) String userId) throws Exception {
		
		return userService.getUserDetails(userId);
	}

	@PostMapping("/create-account")
	public String createUserAccount(@RequestBody UserDTO request) throws Exception{

		return userService.createUserAccount(request);
	}

	public String updateUserAccount(@RequestBody UserDTO request) throws Exception{
		return userService.updateUserAccount(request);
	}

}
