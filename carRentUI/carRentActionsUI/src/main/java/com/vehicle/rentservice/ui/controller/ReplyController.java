package com.vehicle.rentservice.ui.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vehicle.rentservice.model.dto.ReplyPageData;
import com.vehicle.rentservice.model.entity.Reply;
import com.vehicle.rentservice.model.request.RemoveReplyRequest;
import com.vehicle.rentservice.service.api.ReplyService;

@Controller
public class ReplyController {
	
	private @Autowired ReplyService replyService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/admin/replies/page/{page}", method = RequestMethod.GET)
	public String viewReplies(ModelMap model, @PathVariable("page") Integer page) {
		ReplyPageData replies = replyService.getReplies(page);
		model.addAttribute("page", page);
		model.addAttribute("viewResponse", replies);
		return "admin/view-replies";
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(path = "/admin/reply/remove", method = RequestMethod.POST)
	public void removeReply(@RequestBody RemoveReplyRequest request) {
		replyService.removeReply(request.getReplyId());
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(path = "/reply/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertReply(@RequestBody Reply reply) {
		replyService.insertReply(reply); 
	}
}
