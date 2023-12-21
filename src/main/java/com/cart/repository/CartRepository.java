package com.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.model.Cart;
import com.cart.model.User;
import com.cart.payload.UserDto;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	
	Optional<Cart> findByUser(User user);
	
	

}
