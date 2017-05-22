package com.vehicle.rentservice.service.api;

import org.springframework.security.authentication.InsufficientAuthenticationException;

import com.vehicle.rentservice.model.entity.Account;
import com.vehicle.rentservice.model.entity.User;

public interface UserService {
	
	void registerUser(User user);
	
	void updatePassword(User user, String newPassword, String oldPassword) throws InsufficientAuthenticationException;
	
	Boolean checkIfExistsByEmail(String email);
	
	User getByUsername(String username);
	
	void updateAccountInfo(Account account, Long additionalFunds);
	
	User getUser(Long id);
}
