package com.blog.service;

import com.blog.exception.UserException;
import com.blog.model.User;

public interface UserService {
	
	public User registerUser(User user)throws UserException;
	
}
