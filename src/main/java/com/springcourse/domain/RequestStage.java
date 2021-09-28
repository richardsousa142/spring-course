package com.springcourse.domain;

import java.sql.Date;

import com.springcourse.domain.enums.RequestState;

import lombok.Data;

@Data
public class RequestStage {
	private Long id;
	private Date realizationDate;
	private String descriptionOfStage;
	private RequestState stateOfRequest; 
	private Request request;
	private User userRequestOfStage;
}
