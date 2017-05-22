package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByEmail(String email);
	
	User findByUsername(String username);
	
}
