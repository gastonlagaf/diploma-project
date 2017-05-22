package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
