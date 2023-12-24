package com.cart.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cart.constants.AppConstants;
import com.cart.exception.ResourceNotFoundException;
import com.cart.model.Cart;
import com.cart.model.Iteam;
import com.cart.model.Roles;
import com.cart.model.User;
import com.cart.payload.CartDto;
import com.cart.payload.UserDto;
import com.cart.repository.CartRepository;
import com.cart.repository.RoleRepository;
import com.cart.repository.UserRepository;
import com.cart.service.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository repository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository  roleRepository;

	@Override
	public UserDto createUser(UserDto dto) {

		User user = this.mapper.map(dto, User.class);
		User saveUser = this.repository.save(user);

		return this.mapper.map(saveUser, UserDto.class);
	}

	@Override
	public UserDto getSingleUser(Integer userId) {

		User user = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND + userId));

		return this.mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(Integer userId, UserDto dto) {

		User user = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND + userId));
		user.setAddress(dto.getAddress());
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPhoneNo(dto.getPhoneNo());
//		user.setRoles(dto.getRoles());

		User updatedUser = this.repository.save(user);

		return this.mapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND + userId));
		this.repository.delete(user);

	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.repository.findAll();

		List<UserDto> dtos = users.stream().map(user -> this.mapper.map(user, UserDto.class))
				.collect(Collectors.toList());

		return dtos;
	}

	@Override
	public UserDto findByEmail(String email) {
		User user = this.repository.findByEmail(email);

		return this.mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.mapper.map(userDto,User.class);
		// Encode Password
		user.setPhoneNo(this.encoder.encode(user.getPassword()));
		// roles
		Roles roles = this.roleRepository.findById(AppConstants.CUSTOMER).get();
		user.getRoles().add(roles);
		
		User newUser = this.repository.save(user);
		UserDto dto = this.mapper.map(newUser, UserDto.class);
		
		return dto;
	}

}
