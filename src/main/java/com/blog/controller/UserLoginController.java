package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.LoginException;
import com.blog.model.CurrentUserSession;
import com.blog.model.Login;
import com.blog.service.UserLogin;

@RestController
@RequestMapping("/userlogin")
public class UserLoginController {
	
	@Autowired
	private UserLogin cl;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> userLogin(@RequestBody Login dto) throws LoginException{
		CurrentUserSession res=cl.logIntoAccount(dto);
		return new ResponseEntity<CurrentUserSession>(res,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> userLogout(@RequestParam(required = false)String key) throws LoginException{
		String res=cl.logOutFromAccount(key);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}

}
