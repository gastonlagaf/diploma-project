package com.vehicle.rentservice.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.vehicle.rentservice.model.attendency.BodyTypeAttendancyStatisticsData;
import com.vehicle.rentservice.model.enumerations.BodyTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BODY_TYPES", schema = "CAR_RENT_SERVICE")
@NoArgsConstructor
@AllArgsConstructor
public class BodyType {
	
	@Id
	@Column(name = "BODY_TYPE_ID")
	@SequenceGenerator(name = "BODY_TYPES_SEQ", sequenceName = "BODY_TYPES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "BODY_TYPES_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "NAME")
	@Enumerated(EnumType.STRING)
	@Getter @Setter private BodyTypes name;
	
	@OneToMany(mappedBy = "bodyType")
	@Getter private List<BodyTypeAttendancyStatisticsData> attendencyStats; 
}
