package com.springcourse.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Request {
	private Long id;
	private String subject;
	private String descriptionOfRequest;
	private Date creationdDate;
	private RequestStage stateOfRequest;
	private User userOfRequest;
	private List<RequestStage> stagesOfRequests = new ArrayList<RequestStage>();
	
}
