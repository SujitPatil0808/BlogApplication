package com.cart.service;

import com.cart.payload.BillDto;

public interface BillServiceI {
	
	BillDto generateBill (BillDto billDto,Integer userId) throws Exception;

}
