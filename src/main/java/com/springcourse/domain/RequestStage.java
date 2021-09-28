package com.springcourse.domain;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.springcourse.domain.enums.RequestState;

import lombok.Data;

@Data
@Entity(name = "request_stage")
public class RequestStage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "realization_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date realizationDate;
	
	@Column(columnDefinition = "text", name = "description_of_stage")
	private String descriptionOfStage;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state_of_request", nullable = false, length = 12)
	private RequestState stateOfRequest; 
	
	@ManyToOne
	@JoinColumn(name = "request_id", nullable = false)
	private Request request;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userRequestOfStage;
}
