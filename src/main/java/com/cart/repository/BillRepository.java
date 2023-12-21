package com.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {

}
