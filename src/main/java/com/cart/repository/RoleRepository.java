package com.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
