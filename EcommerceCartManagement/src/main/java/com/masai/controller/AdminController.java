package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.ProductException;
import com.masai.model.Product;
import com.masai.service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add-product")
	public ResponseEntity<Product> addProductsHandler(@Valid @RequestBody Product product) throws ProductException {
		Product newProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
	}

}
