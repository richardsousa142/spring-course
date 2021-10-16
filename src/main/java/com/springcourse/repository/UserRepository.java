package com.springcourse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "SELECT u FROM user u WHERE u.email = ?1 AND u.password = ?2")
	public Optional<User> login(String email, String password);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query(value = "UPDATE user u SET u.role_of_user = ?2 WHERE u.id = ?1", nativeQuery = true)
	public int updateRoleOfUser(Long id, String role);
	
	
	public Optional<User> findByEmail(String email);
}
	