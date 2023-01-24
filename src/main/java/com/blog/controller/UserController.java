package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.UserException;
import com.blog.model.User;
import com.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService users;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerCustomer(@Valid @RequestBody User user) throws UserException {
		User c=users.registerUser(user);
		return new ResponseEntity<User>(c,HttpStatus.ACCEPTED);
	}

}
