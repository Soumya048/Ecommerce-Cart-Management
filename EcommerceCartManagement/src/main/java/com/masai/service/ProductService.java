package com.masai.service;

import com.masai.exception.ProductException;
import com.masai.model.Product;

public interface ProductService {

	public Product addProduct(Product product) throws ProductException;
	public Product removeProduct(Integer productId) throws ProductException;
 
	
}
