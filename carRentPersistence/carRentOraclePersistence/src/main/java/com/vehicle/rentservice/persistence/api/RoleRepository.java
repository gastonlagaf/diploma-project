package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.Role;
import com.vehicle.rentservice.model.enumerations.Roles;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleName(Roles roleName);
	
}
