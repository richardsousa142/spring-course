package com.springcourse.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;

@RestController
@RequestMapping(value="requests")
public class RequestResource {
	@Autowired
	private RequestService requestService;
	@Autowired
	private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<Request> saveUser(@RequestBody Request request){
		Request createdRequest = requestService.saveRequest(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Request> getRequestById(@PathVariable(name = "id") Long id){
		Request request = requestService.getRequestById(id);
		return ResponseEntity.ok(request);
	}
	@GetMapping
	public ResponseEntity<List<Request>> listAllByUserOfRequestId(){
		List<Request> listOfAllRequests = requestService.listOfRequests();
		return ResponseEntity.ok(listOfAllRequests);
	}
	@GetMapping("/{id}/request-stage")
	public ResponseEntity<List<RequestStage>> listAllByRequestStageId(@PathVariable(name = "id") Long id){
		List<RequestStage> listOfRequestStagesById = requestStageService.listAllByRequestStageId(id);
		return ResponseEntity.ok(listOfRequestStagesById);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Request> updateUser(@PathVariable(name = "id") Long id, @RequestBody Request request){
		request.setId(id);
		Request updatedRequest = requestService.updateRequest(request);
		return ResponseEntity.ok(updatedRequest);
	}
}