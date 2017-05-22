package com.vehicle.rentservice.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne; 
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "CONTRACTS", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
	
	@Id
	@Column(name = "CONTRACT_ID")
	@SequenceGenerator(name = "CONTRACTS_SEQ", sequenceName = "CONTRACTS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "CONTRACTS_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@NotNull
	@Column(name = "OPEN_DATE")
	@Getter @Setter private LocalDate openDate;
	
	@NotNull
	@Column(name = "CLOSE_DATE")
	@Getter @Setter private LocalDate closeDate;
	
	@Column(name = "PRICE")
	@Getter @Setter private Long totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@Getter @Setter private User user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CAR_RENT_SERVICE", name = "CONTRACTS_VEHICLES",
		joinColumns = @JoinColumn(name = "CONTRACT_ID"),
		inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID"))
	@Getter @Setter private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "contract")
	@Getter @Setter private List<Debt> debts;
	
	@Column(name = "ACTIVE", columnDefinition = "NUMBER(1)")
	@Getter @Setter Boolean active;
	
	@Column(name = "PAID", columnDefinition = "NUMBER(1)")
	@Getter @Setter Boolean paid;
	
	@Column(name = "CLOSED", columnDefinition = "NUMBER(1)")
	@Getter @Setter Boolean closed;
	
}
