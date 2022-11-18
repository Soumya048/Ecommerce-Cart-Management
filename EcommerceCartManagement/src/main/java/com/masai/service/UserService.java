package com.masai.service;

import com.masai.exception.UserException;
import com.masai.model.User;

public interface UserService {

	public User registerUser(User user) throws UserException;
	
	
}
