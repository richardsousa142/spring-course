package com.springcourse.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springcourse.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "request")
public class Request implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String subject;
	
	@Column(columnDefinition = "text", name = "description_of_request")
	private String descriptionOfRequest;
	
	@Column(name = "creation_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationdDate;
	
	@Column(name = "state_of_request", nullable = false, length = 12)
	@Enumerated(EnumType.STRING)
	private RequestState stateOfRequest;
	
	@ManyToOne
	@JoinColumn(name = "user_of_request_id", nullable = false)
	private User userOfRequest;
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "request")
	private List<RequestStage> stagesOfRequests = new ArrayList<RequestStage>();
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "request")
	private List<RequestFile> files = new ArrayList<RequestFile>();
}
	