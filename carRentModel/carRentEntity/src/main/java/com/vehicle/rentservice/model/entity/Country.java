package com.vehicle.rentservice.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.vehicle.rentservice.model.attendency.CountryAttendancyStatisticsData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COUNTRIES", schema = "CAR_RENT_SERVICE")
@AllArgsConstructor
@NoArgsConstructor
public class Country {

	@Id
	@Column(name = "COUNTRY_ID")
	@SequenceGenerator(name = "COUNTRIES_SEQ", sequenceName = "COUNTRIES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "COUNTRIES_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "COUNTRY_NAME")
	@Getter @Setter private String name;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
	@Getter @Setter private List<City> cities;
	
	@OneToMany(mappedBy = "country")
	private List<CountryAttendancyStatisticsData> attendencyStats;
}
