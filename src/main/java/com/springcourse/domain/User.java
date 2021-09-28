package com.springcourse.domain;

import java.util.ArrayList;
import java.util.List;

import com.springcourse.domain.enums.Role;

import lombok.Data;

import lombok.AllArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	private Role roleOfUser; 
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stageOfRequests = new ArrayList<RequestStage>();
}
