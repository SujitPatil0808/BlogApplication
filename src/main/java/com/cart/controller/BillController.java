package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.payload.BillDto;
import com.cart.service.BillServiceI;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/bill")
@Slf4j
public class BillController {

	@Autowired
	private BillServiceI billServiceI;

	@PostMapping("/userId/{userId}")
	public ResponseEntity<BillDto> generateBill(@PathVariable Integer userId, @RequestBody BillDto dto)
			throws Exception {
		log.info("Entering The Request For Generate Bill  With User Id :{}", userId);
		BillDto generateBill = this.billServiceI.generateBill(dto, userId);
		log.info("Completed The Request For Generate Bill  With User Id :{}", userId);
		return new ResponseEntity<BillDto>(generateBill, HttpStatus.CREATED);
	}
}
