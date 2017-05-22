package com.vehicle.rentservice.service.api;

import com.vehicle.rentservice.model.dto.ReplyPageData;
import com.vehicle.rentservice.model.entity.Reply;

public interface ReplyService {
	
	Reply insertReply(Reply reply);
	
	Reply getReply(Long id);
	
	ReplyPageData getReplies(int page);
	
	ReplyPageData getReplies(int page, int repliesPerPage);
	
	void removeReply(Long id);
}
