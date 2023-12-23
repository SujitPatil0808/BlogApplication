package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.model.Cart;
import com.cart.payload.CartDto;
import com.cart.payload.UserDto;
import com.cart.service.CartService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/cart")
@Slf4j
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/")
	public ResponseEntity<CartDto> createCart(@RequestBody CartDto cart){
		log.info("Entering The Request For Save Cart");
		CartDto crateCart = this.cartService.crateCart(cart);
		log.info("Completed The Request For Save Cart");
		return new ResponseEntity<CartDto>(crateCart,HttpStatus.CREATED);
	}
	
	@PostMapping("/cartId/{cartId}/userId/{userId}")
	public ResponseEntity<CartDto> assignCreatedCartToUser(@PathVariable Integer cartId,@PathVariable Integer userId){
		log.info("Entering The Request For Save Cart With User Id :{}",userId);
		CartDto cart = this.cartService.assignCreatedCartToUser(cartId, userId);
		log.info("Completed The Request For Save Cart With User Id :{}",userId);
		 return new ResponseEntity<CartDto>(cart,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/userId/{userId}")
	public ResponseEntity<CartDto> getCartByUserId(@PathVariable Integer userId){
		log.info("Entering The Request For Get Cart With User Id :{}",userId);
		CartDto cartByUserId = this.cartService.getCartByUserId(userId);
		log.info("Completed The Request For Get Cart With User Id :{}",userId);
		return new ResponseEntity<CartDto>(cartByUserId,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
