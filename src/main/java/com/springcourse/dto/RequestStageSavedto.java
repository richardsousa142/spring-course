package com.springcourse.dto;

import javax.validation.constraints.NotNull;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RequestStageSavedto {
	
	private String descriptionOfStage;
	
	@NotNull(message = "state required")
	private RequestState stateOfRequest;
	
	@NotNull(message = "request required")
	private Request request;
	
	@NotNull(message = "user request of stage required")
	private User userRequestOfStage;

	public RequestStage transformToRequestStage() {
		RequestStage requestStage = new RequestStage(null, null, descriptionOfStage, stateOfRequest, request, userRequestOfStage);
		return requestStage;
	}
}
