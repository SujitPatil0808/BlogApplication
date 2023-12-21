package com.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
