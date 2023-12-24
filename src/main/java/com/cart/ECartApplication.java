package com.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cart.constants.AppConstants;
import com.cart.model.Roles;
import com.cart.repository.RoleRepository;

@SpringBootApplication
public class ECartApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepository  roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ECartApplication.class, args);
		System.out.println("Application Started Sucessfully !!!");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("8087707589"));
		
		try {
			Roles roles=new Roles();
			roles.setRoleId(AppConstants.ADMIN);
			roles.setName("ROLE_ADMIN");
			
			Roles roles2=new Roles();
			roles2.setRoleId(AppConstants.CUSTOMER);
			roles2.setName("ROLE_NORMAL");
			
			List<Roles> ROLE = List.of(roles, roles2);
			List<Roles> res = this.roleRepository.saveAll(ROLE);
			
			res.forEach(r -> {
				System.out.println(r.getName());
				});
			
		}catch (Exception e) {
			e.printStackTrace();
		}
				
	}
		
	}


