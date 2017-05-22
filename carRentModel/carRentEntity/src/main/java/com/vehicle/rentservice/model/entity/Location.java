package com.vehicle.rentservice.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LOCATIONS", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class Location {

	@Id
	@Column(name = "LOCATION_ID")
	@SequenceGenerator(name = "LOCATIONS_SEQ", sequenceName = "LOCATIONS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "LOCATIONS_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "LOCATION_NAME")
	@Getter @Setter private String name;
	
	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	@Getter @Setter private City city;
	
	@OneToMany(mappedBy = "location")
	@Getter @Setter private List<Vehicle> vehicles;
	
}
