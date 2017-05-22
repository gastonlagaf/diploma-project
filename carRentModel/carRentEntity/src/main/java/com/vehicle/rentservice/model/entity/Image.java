package com.vehicle.rentservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "IMAGES", schema = "CAR_RENT_SERVICE")
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	
	@Id
	@Column(name = "IMAGE_ID")
	@SequenceGenerator(name = "IMAGES_SEQ", sequenceName = "IMAGES_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "IMAGES_SEQ", strategy = GenerationType.SEQUENCE)
	@Getter private Long id;
	
	@Column(name = "IMAGE_PATH")
	@Getter @Setter private String imagePath;
	
	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID")
	@Getter @Setter private Vehicle vehicle;
	
}
