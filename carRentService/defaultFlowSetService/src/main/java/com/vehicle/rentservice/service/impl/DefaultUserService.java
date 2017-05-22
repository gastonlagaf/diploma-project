package com.vehicle.rentservice.service.impl;

import javax.inject.Inject;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.rentservice.model.entity.Account;
import com.vehicle.rentservice.model.entity.Role;
import com.vehicle.rentservice.model.entity.User;
import com.vehicle.rentservice.model.enumerations.Roles;
import com.vehicle.rentservice.persistence.api.AccountRepository;
import com.vehicle.rentservice.persistence.api.ContractRepository;
import com.vehicle.rentservice.persistence.api.RoleRepository;
import com.vehicle.rentservice.persistence.api.UserRepository;
import com.vehicle.rentservice.service.api.UserService;

@Service
public class DefaultUserService implements UserService {

	@Inject
	private Md5PasswordEncoder encoder;

	@Inject
	private UserRepository repository;

	@Inject
	private RoleRepository roleRepository;
	
	@Inject
	private AccountRepository accountRepository;
	
	@Inject
	private ContractRepository contractRepository;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void registerUser(User user) {
		Role role = roleRepository.findByRoleName(Roles.ROLE_USER);
		Account account = new Account();
		account.setBalance(0L);
		account = accountRepository.save(account);
		user.setAccount(account);
		user.setRole(role);
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		repository.save(user);
	}

	@Override
	public Boolean checkIfExistsByEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	public void updatePassword(User user, String newPassword, String oldPassword)
			throws InsufficientAuthenticationException {
		String md5OldPassword = encoder.encodePassword(oldPassword, null);
		if (md5OldPassword.equals(user.getPassword())) {
			user.setPassword(encoder.encodePassword(newPassword, null));
			repository.save(user);
		} else {
			throw new InsufficientAuthenticationException("Old password does not match");
		}
	}

	@Override
	public User getByUsername(String username) {
		User user = repository.findByUsername(username);
		user.setContracts(contractRepository.findByUserAndClosedFalse(user));
		return user;
	}
	
	@Override
	public void updateAccountInfo(Account account, Long additionalFunds) {
		if(additionalFunds < 0) {
			throw new RuntimeException("Funds cannot be negative");
		}
		Long currentBalance = account.getBalance();
		account.setBalance(currentBalance + additionalFunds);
		accountRepository.save(account);
	}
	
	@Override
	public User getUser(Long id) {
		return repository.findOne(id);
	}

}
