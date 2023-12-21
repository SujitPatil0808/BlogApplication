package com.cart.configuration;

import org.springframework.batch.item.ItemProcessor;


import com.cart.model.Iteam;
import com.cart.payload.IteamInput;


public class IteamProcessor implements ItemProcessor<IteamInput, Iteam>{

	@Override
	public Iteam process(IteamInput item) throws Exception {
		 Iteam iteams = Iteam.builder()
		.iteamId(item.getIteamId())
		.iteamName(item.getIteamName())
		.SellingPrice(item.getSellingPrice())
		.units(item.getUnits()).build();
		return iteams;
	}

	

}
