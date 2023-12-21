package com.cart.service;

import java.util.List;

import com.cart.payload.UserDto;



public interface UserServiceI {
	
	
	UserDto createUser(UserDto dto);
	
	UserDto getSingleUser(Integer userId);
	
	UserDto updateUser(Integer userId,UserDto dto);
	
	void deleteUser(Integer userId);
	
	List<UserDto> getAllUsers();

}
