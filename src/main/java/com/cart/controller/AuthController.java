package com.cart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.payload.JwtAuthRequest;
import com.cart.payload.JwtAuthResponse;
import com.cart.payload.UserDto;
import com.cart.security.JwtTokenHelper;
import com.cart.service.UserServiceI;
import com.cart.serviceImpl.CustomeUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceI serviceI;

		
	@GetMapping("/current")
	public ResponseEntity<UserDetails>getCurrentUser(Principal principal){
		String name = principal.getName();
		return new ResponseEntity<UserDetails>(customeUserDetailsService.loadUserByUsername(name),HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {

		authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());

		UserDetails userDetails = this.customeUserDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(token);

		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);

		try {

			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException b) {

			throw new Exception("Invalid Username or Password !!");
		}
	}
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto dto){
		UserDto registerNewUser = this.serviceI.registerNewUser(dto);
		return new ResponseEntity<UserDto>(registerNewUser,HttpStatus.CREATED);
	}
	
}
