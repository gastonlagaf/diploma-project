package com.vehicle.rentservice.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.vehicle.rentservice.model.attendency.BrandAttendancyStatisticsData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BRANDS", schema = "CAR_RENT_SERVICE")
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
	
	@Id
	@Column(name = "BRAND_ID")
	@SequenceGenerator(name = "BRANDS_SEQ", sequenceName = "BRANDS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "BRANDS_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "NAME")
	@Getter @Setter private String name;
	
	@OneToMany(mappedBy = "brand")
	@Getter private List<BrandAttendancyStatisticsData> attendencyStats;
	
}
