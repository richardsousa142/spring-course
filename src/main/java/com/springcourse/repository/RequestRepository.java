package com.springcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	public List<Request> findAllUserOfRequestById(Long id);
	
	@Query(value="UPDATE request SET stateOfRequest = ?2 WHERE id = ?1", nativeQuery = true)
	public Request updateStatus(Long id, RequestState state);
}
