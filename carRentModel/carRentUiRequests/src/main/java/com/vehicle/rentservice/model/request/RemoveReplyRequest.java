package com.vehicle.rentservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class RemoveReplyRequest {

	@Getter @Setter private Long replyId;
	
}
