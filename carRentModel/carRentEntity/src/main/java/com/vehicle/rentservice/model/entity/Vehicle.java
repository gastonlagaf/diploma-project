package com.vehicle.rentservice.model.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VEHICLES", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
	
	@Id
	@Column(name = "VEHICLE_ID")
	@SequenceGenerator(name = "VEHICLES_SEQ", sequenceName = "VEHICLES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "VEHICLES_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;

	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	@Getter @Setter private Brand brand;

	@Column(name = "MODEL")
	@NotNull
	@Size(min = 1, max = 64)
	@Getter @Setter private String model;
	
	@Column(name = "PRICE")
	@NotNull
	@Getter @Setter private Integer price;

	@Column(name = "MANUFACTURE_YEAR")
	@Getter @Setter private Integer manufactureYear;

	@ManyToOne
	@JoinColumn(name = "BODY_TYPE_ID")
	@Getter @Setter private BodyType bodyType;
	
	@ManyToOne
	@JoinColumn(name = "CURRENT_CONTRACT_ID")
	@Getter @Setter private Contract currentContract;
	
	@ManyToMany(mappedBy = "vehicles")
	@Getter @Setter private List<Contract> contracts;
	
	@NotNull
	@Size(min = 1)
	@OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER)
	@Getter @Setter private List<Image> images;
	
	@Column(name = "URI")
	@Getter private String uri;

	@Column(name = "ARCHIVED", columnDefinition = "NUMBER(1)")
	@Getter @Setter private boolean archived;
	
	@Column(name = "PREORDERED", columnDefinition = "NUMBER(1)")
	@Getter @Setter private boolean preordered;
	
	@ManyToOne
	@JoinColumn(name = "LOCATION_ID")
	@Getter @Setter private Location location;
	
	public void setUri() {
		this.uri = this.brand.getName() + "-" + this.model + "-" + LocalTime.now().format(DateTimeFormatter.ofPattern("HHSS"));
	}
	
}
