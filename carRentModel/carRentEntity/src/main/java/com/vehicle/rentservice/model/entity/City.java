package com.vehicle.rentservice.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "CITIES", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class City {

	@Id
	@Column(name = "CITY_ID")
	@SequenceGenerator(name = "CITIES_SEQ", sequenceName = "CITIES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "CITIES_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "CITY_NAME")
	@Getter @Setter private String name;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	@Getter @Setter private Country country;
	
	@OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
	@Getter @Setter private List<Location> locations;
	
}
