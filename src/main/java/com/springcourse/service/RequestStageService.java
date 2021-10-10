package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.springcourse.domain.RequestStage;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;

@Service
public class RequestStageService {
	@Autowired
	private RequestStageRepository requestStageRepository;
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage saveRequestStage(RequestStage requestStage) {
		requestStage.setRealizationDate(new Date());
		RequestStage createdRequestStage = requestStageRepository.save(requestStage);
		requestRepository.updateStatus(requestStage.getId(), requestStage.getStateOfRequest().toString());
		return createdRequestStage;
	}
	public RequestStage getRequestStageById(Long id) {
		Optional<RequestStage> resultRequestStage = requestStageRepository.findById(id);
		return resultRequestStage.orElseThrow(() -> new NotFoundException("There are not request with id = "+id));
	} 
	public List<RequestStage> listAllByRequestStageId(Long id){
		List<RequestStage> listOfRequestStage = requestStageRepository.findAllByRequestId(id);
		return listOfRequestStage;
	}
	public PageModel<RequestStage> listAllByRequestStageIdOnLazyMode(Long id, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<RequestStage> page = requestStageRepository.findAllByRequestId(id, pageable);
		PageModel<RequestStage> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
}
