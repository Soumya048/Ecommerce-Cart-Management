package com.masai.service;


import java.util.Map;

import com.masai.exception.CartException;
import com.masai.exception.ProductException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.Cart;

public interface CartService {

	public Cart addProductToCart(Integer userId, Integer productId, Integer quantity) throws CartException, UserNotFoundException, ProductException;
	public Cart removeProductFromCart(Integer userId, Integer productId) throws UserNotFoundException, ProductException, CartException ;
	public Map<String, Object> getUserCartData(Integer userId) throws UserNotFoundException, CartException;
	
}
