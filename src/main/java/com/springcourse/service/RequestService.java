package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestRepository;

@Service
public class RequestService {
	@Autowired
	private RequestRepository requestRepository;
	
	public Request saveRequest(Request request) {
		request.setStateOfRequest(RequestState.OPEN);
		request.setCreationdDate(new Date());
		Request createdRequest = requestRepository.save(request);
		return createdRequest;
	}
	
	public Request updateRequest(Request request) {
		Request updatedRequest = requestRepository.save(request);
		return updatedRequest;
	}
	public Request getRequestById(Long id) {
		Optional<Request> resultRequestByid= requestRepository.findById(id);
		return resultRequestByid.orElseThrow(() -> new NotFoundException("There are not request stage with id = "+id));

	}
	public List<Request> listOfRequests(){
		List<Request> listOfRequests = requestRepository.findAll();
		return listOfRequests;
	}
	public List<Request> listAllByUserOfRequestId(Long id){
		List<Request> listOfRequestsByUser = requestRepository.findAllUserOfRequestById(id);
		return listOfRequestsByUser;
	}
	public PageModel<Request> listAllOnLazyMode(PageRequestModel pageRequest){
		Pageable pageAble = PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
		Page<Request> page = requestRepository.findAll(pageAble);
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm; 
	}
	public PageModel<Request> listAllByUserOfRequestIdOnLazyMode(Long id, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAllUserOfRequestById(id, pageable);
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
}
