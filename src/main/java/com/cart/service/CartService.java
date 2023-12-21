package com.cart.service;

import com.cart.model.Cart;
import com.cart.payload.CartDto;
import com.cart.payload.UserDto;

public interface CartService {
	
	
	// Create Cart 
	CartDto crateCart(CartDto cartdto );
	
	// Add Item to Cart
	CartDto assignItemToCart(Integer cartId,Integer userId);
	
	// Get Cart By UserId
	CartDto getCartByUserId(Integer userId);
	
	
	
	

}
