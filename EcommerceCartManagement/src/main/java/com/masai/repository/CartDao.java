package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUserId(Integer userId);
	
}
