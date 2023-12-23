package com.cart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Iteam {

	@Id
	private Integer iteamId;
	
	private String iteamName;
	
	private Integer SellingPrice;
	
	private Integer units;
	
	@ManyToOne
	private Cart cart;
	
	
	

	
	
	
	
}
