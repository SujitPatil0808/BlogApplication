package com.cart.payload;

import java.util.Date;
import java.util.List;

import com.cart.model.Iteam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

	private Integer billId;

	private Integer userId;

	private Integer cartId;

	private Integer totalAmount;

	private Date billDate;

	private Integer quantity;

	private String name;

	private String address;

	private String email;

	private String phoneNo;

	private List<Iteam> allItems;

}
