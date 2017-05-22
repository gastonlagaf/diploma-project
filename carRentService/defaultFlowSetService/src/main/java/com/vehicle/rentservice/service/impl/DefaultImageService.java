package com.vehicle.rentservice.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.entity.Image;
import com.vehicle.rentservice.persistence.api.ImageRepository;
import com.vehicle.rentservice.service.api.ImageService;

@Service
public class DefaultImageService implements ImageService {
	
	@Inject
	private ImageRepository repository;
	
	@Override
	public List<Image> saveImages(List<Image> images) {
		return repository.save(images);
	}
	
}
