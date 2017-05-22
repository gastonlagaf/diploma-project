package com.vehicle.rentservice.persistence.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vehicle.rentservice.model.entity.Location;
import com.vehicle.rentservice.model.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Vehicle v SET v.archived = true WHERE v.id = ?1")
	void archiveVehicle(Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Vehicle v SET v.location = ?2 WHERE v.id = ?1")
	void updateVehicleLocation(Long id, Location location);
	
	@Modifying
	@Transactional
	@Query("UPDATE Vehicle v SET v.price = ?2 WHERE v.id = ?1")
	void updateVehiclePrice(Long id, Integer price);
	
	@Query("SELECT DISTINCT car.manufactureYear FROM Vehicle car")
	List<String> findDistinctManufactureYears();
	
	@Query("SELECT car FROM Vehicle car "
			+ "JOIN FETCH car.currentContract "
			+ "WHERE car.currentContract.openDate BETWEEN ?1 AND ?2")
	List<Vehicle> findOrderedVehiclesInPeriod(LocalDate from, LocalDate to);
	
	@Query("SELECT v FROM Vehicle v WHERE v.preordered = false AND v.currentContract = null AND v.archived = ?1")
	Page<Vehicle> findVehicles(Pageable pageRequest, Boolean archived);
	
	Page<Vehicle> findByPreorderedFalseAndCurrentContractIdNull(Pageable pageRequest);

	Vehicle findByUri(String uri);
	
	List<Vehicle> findVehiclesByIdIn(List<Long> ids);
	
}
