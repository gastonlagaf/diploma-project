package com.vehicle.rentservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.dto.SearchEntity;
import com.vehicle.rentservice.model.entity.Location;
import com.vehicle.rentservice.model.entity.Vehicle;
import com.vehicle.rentservice.persistence.api.VehicleRepository;
import com.vehicle.rentservice.service.api.VehicleService;

import static org.springframework.data.jpa.domain.Specifications.where;
import static com.vehicle.rentservice.persistence.specification.VehicleSearchSpecification.*;

@Service
@PropertySource("classpath:serviceConfiguration.properties")
public class DefaultVehicleService implements VehicleService {

	private final Integer DEFAULT_PAGE_CAPACITY;

	@Inject
	private VehicleRepository repository;

	@Autowired
	public DefaultVehicleService(@Value("${vehicle.page.capacity:10}") Integer defaultCapacity) {
		this.DEFAULT_PAGE_CAPACITY = defaultCapacity;
	}

	@Override
	public Page<Vehicle> getVehicles(Integer page) {
		return getVehicles(page, DEFAULT_PAGE_CAPACITY);
	}
	
	@Override
	public Page<Vehicle> getVehicles(Integer page, Boolean archived) {
		return getVehicles(page, DEFAULT_PAGE_CAPACITY, false);
	}
	
	@Override
	public Page<Vehicle> getVehicles(Integer page, Integer vehiclesPerPage) {
		return getVehicles(page, vehiclesPerPage, false);
	}

	@Override
	public Page<Vehicle> getVehicles(Integer page, Integer vehiclesPerPage, Boolean archived) {
		PageRequest pageRequest = new PageRequest(page - 1, vehiclesPerPage);
		return repository.findVehicles(pageRequest, archived);
	}

	@Override
	public List<Vehicle> getVehiclesByIds(List<Long> ids) {
		return repository.findVehiclesByIdIn(ids);
	}

	@Override
	public Vehicle getVehicle(Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public List<Vehicle> getOrderedVehiclesInPeriod(LocalDate from, LocalDate to) {
		return repository.findOrderedVehiclesInPeriod(from, to);
	}

	@Override
	public void insertVehicle(Vehicle vehicle) {
		repository.save(vehicle);
	}

	@Override
	public void updateVehiclePrice(Long id, Integer price) {
		repository.updateVehiclePrice(id, price);
	}
	
	@Override
	public void updateVehicleLocation(Long id, Location location) {
		repository.updateVehicleLocation(id, location);	
	}

	@Override
	public Vehicle markVehicleAsOrdered(Long id) {
		Vehicle vehicle = repository.findOne(id);
		vehicle.setPreordered(true);
		repository.save(vehicle);
		return vehicle;
	}

	@Override
	public void unmarkVehiclesAsOrdered(List<Vehicle> vehicles) {
		vehicles.stream().map(vehicle -> {
			vehicle.setPreordered(false);
			return vehicle;
		}).collect(Collectors.toList());
		repository.save(vehicles);
	}

	@Override
	public void archiveVehicle(Long id) {
		repository.archiveVehicle(id);
	}

	@Override
	public void disarchiveVehicle(Long id) {
		Vehicle vehicle = repository.findOne(id);
		if (vehicle.isArchived()) {
			vehicle.setArchived(false);
		}
	}

	@Override
	public List<String> getAllCarsManufactureYears() {
		return repository.findDistinctManufactureYears();
	}

	@Override
	public Vehicle getVehicleByUri(String uri) {
		return repository.findByUri(uri);
	}

	@Override
	public void insertVehicles(List<Vehicle> vehicles) {
		repository.save(vehicles);
	}
	
	@Override
	public Page<Vehicle> searchVehicles(SearchEntity searchEntity, Integer page) {
		List<Specification<Vehicle>> specs = new ArrayList<>();
		specs.add(unordered());
		specs.add(notArchived());
		Specification<Vehicle> spec = null;
		if(Objects.nonNull(searchEntity.getCountry())) {
			specs.add(where(isInCountry(searchEntity.getCountry())));
		}
		if(Objects.nonNull(searchEntity.getCity())) {
			specs.add(where(isInCity(searchEntity.getCity())));
		}
		if(Objects.nonNull(searchEntity.getLocation())) {
			specs.add(where(isInLocation(searchEntity.getLocation())));
		}
		if(Objects.nonNull(searchEntity.getBrand())) {
			specs.add(where(withBrand(searchEntity.getBrand())));
		}
		if(Objects.nonNull(searchEntity.getBodyType())) {
			specs.add(where(withBodyType(searchEntity.getBodyType())));
		}
		if(Objects.nonNull(searchEntity.getFromYear()) || !Objects.isNull(searchEntity.getToYear())) {
			specs.add(where(betweenYears(searchEntity.getFromYear(), searchEntity.getToYear())));
		}
		if(!specs.isEmpty()) {
			spec = specs.get(0);
			for(int i = 1; i < specs.size(); i++) {
				spec = where(spec).and(specs.get(i));
			}
		}
		
		PageRequest pageRequest = new PageRequest(page - 1, DEFAULT_PAGE_CAPACITY);
		return repository.findAll(spec, pageRequest);
	}

}
