package com.cart.serviceImpl;




import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.constants.AppConstants;
import com.cart.exception.ResourceNotFoundException;
import com.cart.model.Cart;
import com.cart.model.Iteam;
import com.cart.model.User;
import com.cart.payload.CartDto;
import com.cart.payload.IteamDto;
import com.cart.payload.UserDto;
import com.cart.repository.CartRepository;
import com.cart.repository.IteamRepository;
import com.cart.repository.UserRepository;
import com.cart.service.IteamService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IteamServiceImpl implements IteamService {

	@Autowired
	private IteamRepository iteamRepository;

	@Autowired
	private CartRepository cartRepository;

	

	@Autowired
	private ModelMapper mapper;

	@Override
	public IteamDto createItam(IteamDto dto) {
		log.info("Entering The Dao Call For Create Item : ");
		Iteam iteam = this.mapper.map(dto, Iteam.class);
		Iteam saveItam = this.iteamRepository.save(iteam);
		log.info("Completed The Dao Call For Create Item : ");
		return this.mapper.map(saveItam, IteamDto.class);
	}

	@Override
	public CartDto createItamWithCart(Integer itemId, Integer cartId) {
		log.info("Entering The Dao Call For Create Item With Cart Id:{} ",cartId);
		Cart cart = this.cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));

		Iteam item = this.iteamRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));

		item.setCart(cart);
//		List<Iteam> iteams = cart.getIteams();
//		iteams.add(item);

//		cart.setIteams(iteams);
//		Cart saveCart = this.cartRepository.save(cart);
		
		Iteam saveItem = this.iteamRepository.save(item);
		
		Cart cart2 = saveItem.getCart();
		log.info("Completed The Dao Call For Create Item With Cart Id:{} ",cartId);
		return this.mapper.map(cart2, CartDto.class);
	}

	@Override
	public CartDto getIteamByCartId(Integer cartId) {
		log.info("Entering The Dao Call For Create Get Item with  Cart Id:{} ",cartId);
		Cart cart = this.cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));
		log.info("Completed The Dao Call For Create Get Item with  Cart Id:{} ",cartId);
		return this.mapper.map(cart, CartDto.class);
	}

}
