package com.cart.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cart.model.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Integer userId;

	@NotBlank(message = "Name Must Not Be Blank")
	private String name;
	
	@NotBlank(message = "Name Must Not Be Blank")
	@Size(min = 5,max = 50,message = "Address Must Be Minimun Character And Max 50 Character")
	private String address;

	@Email(message = "Your Email Address Not Valid Please Enter Valid Email Address")
	private String email;
	
	@NotBlank(message = "Name Must Not Be Blank")
	private String phoneNo;

//	private Set<Roles> roles = new HashSet<>();
}
