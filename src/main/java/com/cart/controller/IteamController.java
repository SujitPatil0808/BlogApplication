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

import com.cart.payload.CartDto;
import com.cart.payload.IteamDto;
import com.cart.payload.UserDto;
import com.cart.repository.IteamRepository;
import com.cart.service.CartService;
import com.cart.service.IteamService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/iteam")
@Slf4j
public class IteamController {

	@Autowired
	private IteamService iteamService;

	@PostMapping("/")
	public ResponseEntity<IteamDto> createIteams(@RequestBody IteamDto iteamDto) {
		log.info("Entering The Request For Create Items : ");
		IteamDto createItam = this.iteamService.createItam(iteamDto);
		log.info("Completed The Request For Create Items : ");
		return new ResponseEntity<IteamDto>(createItam, HttpStatus.CREATED);
	}

	@PostMapping("/itemId/{itemId}/cartId/{cartId}")
	public ResponseEntity<CartDto> createIteamWithCartId(@PathVariable Integer itemId, @PathVariable Integer cartId) {
		log.info("Entering The Request For Create Items With Cart Id  And Item Id:{} :{}", cartId, itemId);
		CartDto cart = this.iteamService.createItamWithCart(itemId, cartId);
		log.info("Completed The Request For Create Items With Cart Id  And Item Id:{} :{}", cartId, itemId);

		return new ResponseEntity<CartDto>(cart, HttpStatus.CREATED);
	}

	@GetMapping("/cartId/{cartId}")
	public ResponseEntity<CartDto> getCartById(@PathVariable Integer cartId) {
		log.info("Entering The Request For Get Cart With  Cart Id:{} :{}",cartId);

		CartDto iteamByCartId = this.iteamService.getIteamByCartId(cartId);
		log.info("Completed The Request For Get Cart With  Cart Id:{} :{}",cartId);
		return new ResponseEntity<CartDto>(iteamByCartId, HttpStatus.OK);
	}

}
