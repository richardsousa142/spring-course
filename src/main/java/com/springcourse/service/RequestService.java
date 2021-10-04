package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
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
		return resultRequestByid.get();
	}
	public List<Request> listOfRequests(){
		List<Request> listOfRequests = requestRepository.findAll();
		return listOfRequests;
	}
	public List<Request> listAllByUserOfRequestId(Long id){
		List<Request> listOfRequestsByUser = requestRepository.findAllUserOfRequestById(id);
		return listOfRequestsByUser;
	}
}
