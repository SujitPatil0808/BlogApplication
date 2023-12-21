package com.cart.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.constants.AppConstants;
import com.cart.exception.ResourceNotFoundException;
import com.cart.model.Cart;
import com.cart.model.User;
import com.cart.payload.CartDto;
import com.cart.payload.UserDto;
import com.cart.repository.CartRepository;
import com.cart.repository.UserRepository;
import com.cart.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CartDto crateCart(CartDto cartdto) {
		log.info("Entering The Dao Call For Save Cart ");
		Cart cart = this.mapper.map(cartdto, Cart.class);
		
		Cart savedCart = this.cartRepository.save(cart);
		CartDto cartDto = this.mapper.map(savedCart, CartDto.class);
		log.info("Completed The Dao Call For Save Cart ");
		return cartDto;
	}

	@Override
	public CartDto assignItemToCart(Integer cartId, Integer userId) {
		log.info("Entering The Dao Call For Create  Cart With User Id :{} ",userId);
		User user = this.userRepository.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException(AppConstants.NOT_FOUND+userId));
		
		Cart cart = this.cartRepository.findById(cartId).
		orElseThrow(()-> new ResourceNotFoundException(AppConstants.NOT_FOUND+cartId));
		
		
		cart.setUser(user);
		Cart savedCart = this.cartRepository.save(cart);
		log.info("Completed The Dao Call For Create  Cart With User Id :{} ",userId);
		return this.mapper.map(savedCart, CartDto.class);
	}

	@Override
	public CartDto getCartByUserId(Integer userId) {
		log.info("Entering The Dao Call For Get Cart With User Id :{} ",userId);
		User user = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException(AppConstants.NOT_FOUND));
		
		Cart cart = this.cartRepository.findByUser(user)
				.orElseThrow(()-> new ResourceNotFoundException(AppConstants.NOT_FOUND));
		
		CartDto cartDto = this.mapper.map(cart, CartDto.class);
		log.info("Completed The Dao Call For Get Cart With User Id :{} ",userId);
		return cartDto;
	}

		

}
