package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestUpdatedto {
	
	@NotBlank(message = "subject required")
	private String subject;
	private String descriptionOfRequest;
	
	@NotNull(message = "state required")
	private RequestState stateOfRequest;
	
	@NotNull(message = "user of request required")
	private User userOfRequest;
	
	private List<RequestStage> stagesOfRequests = new ArrayList<RequestStage>();

	
	public Request transformToRequest() {
		Request request = new Request(null, this.subject, this.descriptionOfRequest, null, this.stateOfRequest, this.userOfRequest, this.stagesOfRequests);
		return request;
	} 
}
