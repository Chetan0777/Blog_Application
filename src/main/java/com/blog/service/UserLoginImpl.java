package com.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.LoginException;
import com.blog.model.CurrentUserSession;
import com.blog.model.Login;
import com.blog.model.User;
import com.blog.repository.UserRepo;
import com.blog.repository.UserSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginImpl implements UserLogin{

	@Autowired
	private UserSessionRepo usr;
	
	@Autowired
	private UserRepo crl;
	
	@Override
	public CurrentUserSession logIntoAccount(Login dto) throws LoginException {
		List<User> list=crl.findByEmail(dto.getEmail());
		
		if(list.size()==0) {
			throw new LoginException("please enter valid email");
		}
		
		User user=list.get(0);
		Optional<CurrentUserSession> validation=usr.findById(user.getId());
		
		if(validation.isPresent()) {
			if(user.getPassword().equals(dto.getPassword())) {
				return validation.get();
			}
			throw new LoginException("please enter valid password");
		}
		
		if(user.getPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession cus=new CurrentUserSession();
			cus.setUserId(user.getId());
			cus.setUuid(key);
			cus.setLocalDateTime(LocalDateTime.now());
			usr.save(cus);
			return cus;
		}
		
		throw new LoginException("please enter valid password");
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		List<CurrentUserSession> validation=usr.findByUuid(key);
		if(validation.size()==0) {
			throw new LoginException("user not logged in with this email");
		}
		usr.delete(validation.get(0));
		return "Logged out !";
	}

}
