
package com.vehicle.rentservice.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@Column(name = "USER_ID")
	@SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "USERS_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "USERNAME")
	@NotNull
	@Size(min = 2, max = 32)
	@Getter @Setter private String username;
	
	@Column(name = "PASSWORD")
	@NotNull
	@Size(min = 2, max = 32)
	@Getter @Setter private String password;
	
	@Column(name = "NAME")
	@NotNull
	@Size(min = 2, max = 24)
	@Getter @Setter private String name;
	
	@Column(name = "SURNAME")
	@NotNull
	@Size(min = 2, max = 32)
	@Getter @Setter private String surname;
	
	@Column(name = "PATRONYMIC")
	@Size(max = 32)
	@Getter @Setter private String patronymic;
	
	@Column(name = "EMAIL")
	@NotNull
	@Getter @Setter private String email;
	
	@Column(name = "CONTACT_NUMBER")
	@NotNull
	@Getter @Setter private String contactNumber;
	
	@OneToOne
	@JoinColumn(name = "ROLE_ID")
	@Getter @Setter private Role role;
	
	@OneToOne
	@JoinColumn(name = "ACCOUNT_ID")
	@Getter @Setter private Account account;
	
	@OneToMany(mappedBy = "user")
	@Getter @Setter private List<Contract> contracts;
		
}
