package com.cart.serviceImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.constants.AppConstants;
import com.cart.exception.ResourceNotFoundException;
import com.cart.model.Bill;
import com.cart.model.Cart;
import com.cart.model.Iteam;
import com.cart.model.User;
import com.cart.payload.BillDto;
import com.cart.repository.BillRepository;
import com.cart.repository.UserRepository;
import com.cart.service.BillServiceI;

@Service
public class BillServiceImpl implements BillServiceI {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public BillDto generateBill(BillDto billDto, Integer userId) throws Exception {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND + userId));

		// Retrieve user's cart and items
		Cart cart = user.getUserCart();
		if (cart == null) {

			throw new Exception("User has no cart");
		}
		List<Iteam> items = cart.getIteams();
		System.out.println(items + "!!!!!!!!!!");
		if (items == null) {

			throw new Exception("Cart has no items");
		}

		// Calculate the total amount from the selling prices of items in the cart
		Integer total = calculateTotalAmount(items);
		Integer quantity = getQuantity(items);
		billDto.setQuantity(quantity);
		// Update user information with details from the billDto
		updateUserInformation(user, billDto);

		// Set properties in billDto
		setBillDtoProperties(billDto, cart, total);

		// Map the BillDto to a Bill entity and save it to the repository
		Bill bill = this.mapper.map(billDto, Bill.class);
		Bill savedBill = this.billRepository.save(bill);

		// Map the saved Bill entity back to a BillDto and return it
		return this.mapper.map(savedBill, BillDto.class);
	}

	// Helper method to calculate the total amount from a list of items
	private Integer calculateTotalAmount(List<Iteam> items) {
		Integer total = 0;
		for (Iteam item : items) {
			total += item.getSellingPrice();
		}
		return total;
	}

	private Integer getQuantity(List<Iteam> items) {
		Integer quantity = 0;
		for (Iteam i : items) {
			quantity += 1;
		}
		return quantity;
	}

	// Helper method to update user information with details from the billDto
	private void updateUserInformation(User user, BillDto billDto) {
		user.setAddress(billDto.getAddress());
		user.setEmail(billDto.getEmail());
		user.setName(billDto.getName());
		user.setPhoneNo(billDto.getPhoneNo());

	}

	// Helper method to set properties in the billDto
	private void setBillDtoProperties(BillDto billDto, Cart cart, Integer total) {
		Date date = new Date();
		billDto.setBillDate(date);
		billDto.setTotalAmount(total);
		billDto.setCartId(cart.getCartId());
		billDto.setUserId(cart.getUser().getUserId());

	}

}
