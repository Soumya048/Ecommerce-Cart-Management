package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.ProductException;
import com.masai.model.Product;
import com.masai.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		Optional<Product> productOpt = productDao.findByProductName(product.getProductName());
		
		if(productOpt.isPresent())
			throw new ProductException("Product Already Exist with this name: " + product.getProductName());
		
		return productDao.save(product);
	}

	@Override
	public Product removeProduct(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
