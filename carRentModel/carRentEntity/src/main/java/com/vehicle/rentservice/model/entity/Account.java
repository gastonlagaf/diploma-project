package com.vehicle.rentservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ACCOUNTS", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
	@Id
	@Column(name = "ACCOUNT_ID")
	@SequenceGenerator(name = "ACCOUNTS_SEQ", sequenceName = "ACCOUNTS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "ACCOUNTS_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "AMOUNT")
	@Getter @Setter private Long balance;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	@Getter @Setter private User user;
	
}
