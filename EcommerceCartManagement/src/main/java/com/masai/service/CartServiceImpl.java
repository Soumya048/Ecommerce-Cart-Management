package com.masai.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CartException;
import com.masai.exception.ProductException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.Cart;
import com.masai.model.Product;
import com.masai.model.User;
import com.masai.repository.CartDao;
import com.masai.repository.ProductDao;
import com.masai.repository.UserDao;

@Service
public class CartServiceImpl implements CartService  {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public Cart addProductToCart(Integer userId, Integer productId, Integer quantity) throws CartException, UserNotFoundException, ProductException {
		
		Optional<User> existUserOpt = userDao.findById(userId);
		
		if(existUserOpt.isEmpty())
			throw new UserNotFoundException("User not found with this user id: " + userId);
		
		
		Optional<Product> existproductOpt = productDao.findById(productId);
		
		if(existproductOpt.isEmpty())
			throw new ProductException("Product not found with this product id: " + productId);
			
		
		Product product = existproductOpt.get();
		
		if(quantity < 1)
			throw new CartException("Quantity can't be less than 1");
		
		if(product.getAvailableQuantity() < quantity) 
			throw new ProductException("Not enought quantity available");
		
		
		Cart cart = cartDao.findByUserId(userId).get();
		
		Map<Product, Integer> productList = cart.getProductList();
		
		if(!productList.containsKey(product)) {
			productList.put(product, quantity);
		}
		else {
			if(productList.get(product) + quantity > product.getAvailableQuantity()) 
				throw new ProductException("Not enough product Available");
			
			productList.put(product, productList.get(product) + quantity);
		}
		
		cart.setProductList(productList);
		
		Cart savedCart = cartDao.save(cart);
	
		return savedCart;
	}

	@Override
	public Cart removeProductFromCart(Integer userId, Integer productId) throws UserNotFoundException, ProductException, CartException {
		
		Optional<User> existUserOpt = userDao.findById(userId);
		
		if(existUserOpt.isEmpty())
			throw new UserNotFoundException("User not found with this user id: " + userId);
		
		
		Optional<Product> existproductOpt = productDao.findById(productId);
		
		if(existproductOpt.isEmpty())
			throw new ProductException("Product not found with this product id: " + productId);
		
		Product product = existproductOpt.get();
		
		Cart cart = cartDao.findByUserId(userId).get();
		
		Map<Product, Integer> cartProductList = cart.getProductList();
		
		if (cartProductList.isEmpty()) 
			throw new CartException("Cart is empty");
		
		cartProductList.remove(product);
		
		cart.setProductList(cartProductList);
		
		Cart savedCart = cartDao.save(cart);

		
		return savedCart;
	}

	@Override
	public Map<String, Object> getUserCartData(Integer userId) throws UserNotFoundException, CartException {
		
		Optional<User> existUserOpt = userDao.findById(userId);
		
		if(existUserOpt.isEmpty())
			throw new UserNotFoundException("User not found with this user id: " + userId);
		
		Cart cart = cartDao.findByUserId(userId).get();
		
		Map<Product, Integer> cartProductList = cart.getProductList();
		
		if(cartProductList.isEmpty())
			throw new CartException("Cart is empty");
		
		
		
		
		Double totalMarketPrice = 0.0;
		Double totalSalePrice = 0.0;
		for(Map.Entry<Product, Integer> entry : cartProductList.entrySet()) {
			
			totalMarketPrice += (entry.getKey().getMarkPrice() * entry.getValue());
			totalSalePrice += (entry.getKey().getSalePrice() * entry.getValue());
			
		}
		Double discountedAmount = totalMarketPrice - totalSalePrice;
		Integer discountedPercentage =   (int) Math.floor( (discountedAmount / totalMarketPrice) * 100);
		
		System.out.println(discountedPercentage);
		
		Map<String, Object> cartData = new HashMap<String, Object>();
		
		cartData.put("userId", userId);
		cartData.put("cartId", cart.getCartId());
		cartData.put("productList", cartProductList);
		cartData.put("price", totalMarketPrice);
		cartData.put("discountAmmount", discountedAmount);
		cartData.put("totalAmmount", totalSalePrice);
		cartData.put("percentageOff", discountedPercentage);
		
		return cartData;
	}



	
	
}
