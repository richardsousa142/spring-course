package com.springcourse.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

import lombok.AllArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class User {
	private Long id;
	private String neme;
	private String email;
	private String password;
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stageOfRequests = new ArrayList<RequestStage>();
	
}
