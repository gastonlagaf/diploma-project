package com.vehicle.rentservice.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class InsertVehicleRequest {
	
	@Getter @Setter private String location;
	
	@Getter @Setter private String brand;
	
	@Getter @Setter private String model;
	
	@Getter @Setter private Integer price;
	
	@Getter @Setter private Integer year;
	
	@Getter @Setter private String bodyType;
	
	@Getter @Setter private MultipartFile mainImage;
	
	@Getter @Setter private MultipartFile frontImage;
	
	@Getter @Setter private MultipartFile leftImage;
	
	@Getter @Setter private MultipartFile rightImage;
	
	@Getter @Setter private MultipartFile backImage;
	
}
