package com.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.constants.AppConstants;
import com.cart.payload.ApiResponse;
import com.cart.payload.UserDto;
import com.cart.service.UserServiceI;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServiceI userServiceI;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createUser = this.userServiceI.createUser(userDto);
		return new ResponseEntity<UserDto>(createUser,HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(	@PathVariable	Integer userId){
		UserDto user = this.userServiceI.getSingleUser(userId);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse>deleteUserById(@PathVariable Integer userId){
		ApiResponse api=new ApiResponse();
		api.setHttpStatus(HttpStatus.OK);
		api.setMessage(AppConstants.DELETE);
		api.setStatus(true);
		this.userServiceI.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(api,HttpStatus.OK);
		
	}
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId,@RequestBody UserDto dto){
		UserDto updateUser = this.userServiceI.updateUser(userId, dto);
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUsers(){
		
		List<UserDto> allUsers = this.userServiceI.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getUserById(	@PathVariable	String email){
		UserDto user = this.userServiceI.findByEmail(email);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	

}
