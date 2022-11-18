package com.masai.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.UserException;
import com.masai.model.Cart;
import com.masai.model.Role;
import com.masai.model.User;
import com.masai.repository.CartDao;
import com.masai.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;
	

	@Override
	public User registerUser(User user) throws UserException {
		
		Enum role = user.getUserRole();
		
		if(!role.equals(Role.CUSTOMER))
			throw new UserException("Invalid User Type");
		
		Optional<User> userOpt = userDao.findByPhoneNumber(user.getPhoneNumber());
		
		if(userOpt.isPresent())
			throw new UserException("User already exist with this number: " + user.getPhoneNumber());
		
		User savedUser = userDao.save(user);
		
		Cart userCart = new Cart();
		userCart.setUserId(savedUser.getUserId());
		
		cartDao.save(userCart);
		
		return savedUser;
	}

	
	
}
