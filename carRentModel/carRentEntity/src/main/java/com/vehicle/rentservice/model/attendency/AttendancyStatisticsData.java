package com.vehicle.rentservice.model.attendency;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class AttendancyStatisticsData {
	
	@Id
	@Column(name = "RECORD_ID")
	@SequenceGenerator(name = "ATTENDENCIES_SEQ", sequenceName = "ATTENDENCIES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "ATTENDENCIES_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "TIMES_ATTENDED")
 	@Getter @Setter private Integer timesAttended;
 	
	@Column(name = "MONTH_AND_YEAR")
 	@Getter @Setter private LocalDate period;
	
}
