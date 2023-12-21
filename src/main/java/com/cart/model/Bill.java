package com.cart.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(mappedBy = "bill")
	private List<Iteam> allItems;

}
