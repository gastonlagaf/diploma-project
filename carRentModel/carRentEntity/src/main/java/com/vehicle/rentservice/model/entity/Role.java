package com.vehicle.rentservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.vehicle.rentservice.model.enumerations.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROLES", schema = "CAR_RENT_SERVICE")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	
	@Id
	@Column(name = "ROLE_ID")
	@SequenceGenerator(name = "ROLES_SEQ", sequenceName = "ROLES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "ROLES_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "ROLE_NAME")
	@Enumerated(EnumType.STRING)
	@Getter @Setter private Roles roleName;

}
