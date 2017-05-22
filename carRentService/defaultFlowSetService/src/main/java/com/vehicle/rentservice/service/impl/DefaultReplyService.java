package com.vehicle.rentservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vehicle.rentservice.model.dto.ReplyPageData;
import com.vehicle.rentservice.model.entity.Reply;
import com.vehicle.rentservice.persistence.api.ReplyRepository;
import com.vehicle.rentservice.service.api.ReplyService;

@Service
@PropertySource("classpath:serviceConfiguration.properties")
public class DefaultReplyService implements ReplyService {

	private final Integer DEFAULT_PAGE_CAPACITY;
	
	private @Autowired ReplyRepository repository;
	
	@Autowired
	public DefaultReplyService(@Value("${reply.page.capacity:7}") int pageCapacity) {
		this.DEFAULT_PAGE_CAPACITY = pageCapacity;
	}
	
	@Override
	public Reply insertReply(Reply reply) {
		return repository.save(reply);
	}

	@Override
	public Reply getReply(Long id) {
		return repository.findOne(id);
	}

	@Override
	public ReplyPageData getReplies(int page) {
		return getReplies(page, DEFAULT_PAGE_CAPACITY);
	}

	@Override
	public ReplyPageData getReplies(int page, int repliesPerPage) {
		PageRequest pageRequest = new PageRequest(page - 1, repliesPerPage);
		Page<Reply> resultPage = repository.findAll(pageRequest);
		List<Reply> replies = resultPage.getContent();
		int totalPages = resultPage.getTotalPages();
		return new ReplyPageData(totalPages, replies);
	}

	@Override
	public void removeReply(Long id) {
		repository.delete(id);
	}

}
