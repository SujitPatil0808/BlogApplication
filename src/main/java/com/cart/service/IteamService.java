package com.cart.service;

import com.cart.model.User;
import com.cart.payload.CartDto;
import com.cart.payload.IteamDto;
import com.cart.payload.UserDto;

public interface IteamService {
	
	
	// Create Item 
	IteamDto createItam(IteamDto dto);
	
	//Create item With Cart Id
	CartDto createItamWithCart(Integer itemId,Integer cartId);
	
	//Get Items With  Cart Id
	CartDto getIteamByCartId(Integer cartId);
	
	
	
	

}
