package com.vehicle.rentservice.persistence.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.rentservice.model.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
