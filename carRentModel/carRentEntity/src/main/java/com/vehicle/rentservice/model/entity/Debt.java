package com.vehicle.rentservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DEBTS", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class Debt {
	
	@Id
	@Column(name = "DEBT_ID")
	@SequenceGenerator(name = "DEBTS_SEQ", sequenceName = "DEBTS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "DEBTS_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "DEBT_PRICE")
	@Getter @Setter private Integer closingPrice;
	
	@Column(name = "DEBT_DESCRIPTION")
	@Getter @Setter private String description;
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	@Getter @Setter private User user;
	
	@OneToOne
	@JoinColumn(name = "VEHICLE_ID")
	@Getter @Setter private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name = "CONTRACT_ID")
	@Getter @Setter private Contract contract;

}
