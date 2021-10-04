package com.springcourse.repository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RequestStageRepositoryTests {
	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Test
	@Order(1) 
	public void saveTest() {
		User userRequestOfStage = new User();
		userRequestOfStage.setId(1L);
		Request request = new Request();
		request.setId(1L);
		RequestStage stage = new RequestStage(null, new Date(), 
				"Foi comprado um novo laptop de marda HD com 16GB de ram", RequestState.CLOSED, request, userRequestOfStage);
		RequestStage createdStage = requestStageRepository.save(stage);
		assertThat(createdStage.getId()).isEqualTo(1L);
	}
	@Test 
	public void getByIdTest() {
		Optional<RequestStage> result = requestStageRepository.findById(1L);
		RequestStage stage = result.get();
		assertThat(stage.getDescriptionOfStage()).isEqualTo("Foi comprado um novo laptop de marda HD com 16GB de ram");
	}
	@Test 
	public void listByRequestIdTest() {
		List<RequestStage> stages = requestStageRepository.findAll();
		assertThat(stages.size()).isEqualTo(1);
	}
}
 