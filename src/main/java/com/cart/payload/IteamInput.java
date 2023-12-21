package com.cart.payload;

import lombok.Builder;

@Builder
public class IteamInput {

	private Integer iteamId;
	
	private String iteamName;
	
	private Integer SellingPrice;
	
	private Integer units;
	
	

	
	public Integer getIteamId() {
		return iteamId;
	}

	public void setIteamId(Integer iteamId) {
		this.iteamId = iteamId;
	}

	public String getIteamName() {
		return iteamName;
	}

	public void setIteamName(String iteamName) {
		this.iteamName = iteamName;
	}

	public Integer getSellingPrice() {
		return SellingPrice;
	}

	public void setSellingPrice(Integer sellingPrice) {
		SellingPrice = sellingPrice;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public IteamInput(Integer iteamId, String iteamName, Integer sellingPrice, Integer units) {
		super();
		this.iteamId = iteamId;
		this.iteamName = iteamName;
		SellingPrice = sellingPrice;
		this.units = units;
	}

	public IteamInput() {
		super();
	}
	

}
