package com.vehicle.rentservice.ui.converter;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vehicle.rentservice.model.entity.BodyType;
import com.vehicle.rentservice.model.entity.Brand;
import com.vehicle.rentservice.model.entity.Image;
import com.vehicle.rentservice.model.entity.Location;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.model.enumerations.BodyTypes;
import com.vehicle.rentservice.model.request.InsertVehicleRequest;

@Service("vehicleInsertRequestConverter")
@PropertySource("classpath:ui.properties")
public class VehicleInsertRequestConverter implements RequestConverter<Vehicle, InsertVehicleRequest> {
	
	@Value("${images.location}")
	private String imagesLocation;
	
	@Value("${images.context.path}")
	private String imagesContextPath;
	
	@Override
	public Vehicle convertRequest(InsertVehicleRequest request) {
		Vehicle result = new Vehicle();
		Brand brand = new Brand();
		brand.setName(request.getBrand());
		BodyType bodyType = new BodyType();
		bodyType.setName(BodyTypes.valueOf(request.getBodyType()));
		Location location = new Location();
		location.setName(request.getLocation());
		
		result.setBrand(brand);
		result.setBodyType(bodyType);
		result.setModel(request.getModel());
		result.setManufactureYear(request.getYear());
		result.setImages(processImages(request));
		result.setPrice(request.getPrice());
		result.setLocation(location);
		result.setUri();
		
		result.setImages(result.getImages().stream().map(image -> {
			image.setVehicle(result);
			return image;
		}).collect(Collectors.toList()));
		
		return result;
	}
	
	private List<Image> processImages(InsertVehicleRequest request) {
		List<Image> images = new LinkedList<>();
		images.add(buildImage(request.getMainImage()));
		images.add(buildImage(request.getFrontImage()));
		images.add(buildImage(request.getLeftImage()));
		images.add(buildImage(request.getRightImage()));
		images.add(buildImage(request.getBackImage()));
		return images;
	}
	
	private Image buildImage(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		if(!fileName.endsWith(".jpg")) {
			throw new RequestConvertionException("Inappropriate file extension " + file.getOriginalFilename());
		}
		Image image = new Image();
		image.setImagePath(imagesContextPath + fileName);
		try {
			file.transferTo(new File(imagesLocation + fileName));
		} catch (IllegalStateException | IOException e) {
			throw new RequestConvertionException("File Transfering to local system failed", e);
		}
		return image;
	}
	
}
