package com.blog.service;

import com.blog.exception.LoginException;
import com.blog.model.CurrentUserSession;
import com.blog.model.Login;

public interface UserLogin {
	
	public CurrentUserSession logIntoAccount(Login dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
