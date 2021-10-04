package com.springcourse.repository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RequestRepositoryTests {
	@Autowired
	private RequestRepository requestRepository;
	
	@Test
	@Order(1) 
	public void saveTest() {
		User userOfRequest = new User();
		userOfRequest.setId(1L);
		Request request = new Request(null, "Novo laptop hp", "Pretendo obter um laptop HP", new Date(04, 10, 2021), RequestState.OPEN, 
				userOfRequest, null);
		Request createdRequest = requestRepository.save(request);
		assertThat(createdRequest.getId()).isEqualTo(1L); 
	}
	@Test 
	public void updateTest() {
		User userOfRequest = new User();
		userOfRequest.setId(1L);
		Request request = new Request(1L, "Novo laptop hp", "Pretendo obter um laptop HP, de ram 16GB", null, RequestState.OPEN, 
				userOfRequest, null);
		Request updatedRequest = requestRepository.save(request);
		assertThat(updatedRequest.getDescriptionOfRequest()).isEqualTo("Pretendo obter um laptop HP, de ram 16GB"); 
	}
	@Test 
	public void getByIdTest() {
		Optional<Request> result =  requestRepository.findById(1L);
		Request request = result.get();
		assertThat(request.getSubject()).isEqualTo("Novo laptop hp");
	}
	@Test 
	public void listTest() {
		List<Request> requests = requestRepository.findAll();
		assertThat(requests.size()).isEqualTo(1);
	}
	@Test 
	public void listByuserOfRequestIdTest() {
		List<Request> requests = requestRepository.findAllUserOfRequestById(1L);
		assertThat(requests.size()).isEqualTo(1);
	}
	@Test
	public void updateStatusTest() {
		int affectedRows = requestRepository.updateStatus(1L,"IN_PROGRESS");
		assertThat(affectedRows).isEqualTo(1);
	}
}


















