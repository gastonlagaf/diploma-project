package com.vehicle.rentservice.model.attendency;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vehicle.rentservice.model.entity.Brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BRAND_ATTENDANCY_STATS", schema = "CAR_RENT_SERVICE")
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "RECORD_ID")),
	@AttributeOverride(name = "timesAttended", column = @Column(name = "TIMES_ATTENDED")),
	@AttributeOverride(name = "period", column = @Column(name = "MONTH_AND_YEAR"))
})
@NoArgsConstructor
@AllArgsConstructor
public class BrandAttendancyStatisticsData extends AttendancyStatisticsData {

	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	@Getter @Setter private Brand brand;
	
}
