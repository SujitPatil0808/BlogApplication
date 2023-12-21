package com.cart.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IteamDto {

	public Integer iteamId;

	private String iteamName;

	private Integer SellingPrice;

	private Integer units;

}
