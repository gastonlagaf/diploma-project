package com.vehicle.rentservice.service.api;

import java.util.List;

import com.vehicle.rentservice.model.entity.Image;

public interface ImageService {
	
	List<Image> saveImages(List<Image> images); 
	
}
