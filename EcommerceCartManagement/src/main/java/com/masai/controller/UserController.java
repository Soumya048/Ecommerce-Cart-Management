package com.masai.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CartException;
import com.masai.exception.ProductException;
import com.masai.exception.UserException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.Cart;
import com.masai.model.User;
import com.masai.service.CartService;
import com.masai.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUserHandler(@Valid @RequestBody User user) throws UserException {
		User newUser = userService.registerUser(user);
		return  new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	
	@PatchMapping("/add-product-to-cart/{userId}/{productId}/{quantity}")
	public ResponseEntity<Cart> addProductToCartHandler(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer quantity) throws CartException, UserNotFoundException, ProductException {
		Cart cart = cartService.addProductToCart(userId, productId, quantity);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PatchMapping("/remove-product-from-cart/{userId}/{productId}")
	public ResponseEntity<Cart> removeProductFromCartHandler(@PathVariable Integer userId, @PathVariable Integer productId) throws CartException, UserNotFoundException, ProductException {
		Cart cart = cartService.removeProductFromCart(userId, productId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@GetMapping("/cart-data/{userId}")
	public ResponseEntity<Map<String, Object>> getUserCartDataHandler(@PathVariable Integer userId) throws UserNotFoundException, CartException  {
		Map<String, Object> cartData = cartService.getUserCartData(userId);
		return new ResponseEntity<Map<String, Object>>(cartData, HttpStatus.OK);
	}
	
	

}
