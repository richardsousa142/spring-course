package com.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;


import org.junit.jupiter.api.Order;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Order(1) 
	public void saveTest() {
		User user = new User(null, "luraia","luraia@luraia.com","123",Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);
		assertThat(createdUser.getId()).isEqualTo(1L);
	}
	@Test 
	public void updateTest() {
		User user = new User(32L, "lu rodrigues","luraia@luraia.com","123",Role.ADMINISTRATOR, null, null);
		User updateUser =  userRepository.save(user);
		assertThat(updateUser.getName()).isEqualTo("lu rodrigues");
	}
	@Test
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();
		assertThat(user.getPassword()).isEqualTo("123");
	}
	@Test
	public void listTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(4);
	}
	@Test
	public void loginTest() {
		Optional<User> result = userRepository.login("luraia@luraia.com", "123");
		User loggedUser = result.get();
		assertThat(loggedUser.getId()).isEqualTo(32L);
	}
	@Test
	public void updateRoleOfUserTest() {
		int affectRows = userRepository.updateRoleOfUser(42L, Role.ADMINISTRATOR.toString());
		assertThat(affectRows).isEqualTo(1);
	}
}
  