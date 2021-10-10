package com.springcourse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	public List<Request> findAllUserOfRequestById(Long id);
	public Page<Request> findAllUserOfRequestById(Long id, Pageable pageable);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query(value="UPDATE request r SET r.state_of_request = ?2 WHERE r.id = ?1", nativeQuery = true)
	public int updateStatus(Long id, String stateOfRequest);
}
