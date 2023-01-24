package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.UserException;
import com.blog.model.User;
import com.blog.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo uRepo;

	@Override
	public User registerUser(User user) throws UserException {
		return uRepo.save(user);
	}

}
