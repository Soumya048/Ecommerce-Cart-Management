package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	
	Optional<Product> findByProductName(String productName);
	
}
