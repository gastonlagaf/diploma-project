package com.vehicle.rentservice.model.dto;

import java.util.List;

import com.vehicle.rentservice.model.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ReplyPageData {
	
	@Getter @Setter private Integer totalPages;
	
	@Getter @Setter private List<Reply> replies;
	
}
