package com.springcourse.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLogindto;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

@RestController
@RequestMapping(value="users")
public class UserResource {
	@Autowired
	private UserService userService;
	@Autowired
	private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User createdUser = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id){
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> listOfAllUsers = userService.listAllUsers();
		return ResponseEntity.ok(listOfAllUsers);
	} 
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> getAllUserRequestsById(@PathVariable(name = "id") Long id){
		List<Request> listOfRequestByUser = requestService.listAllByUserOfRequestId(id);
		return ResponseEntity.ok(listOfRequestByUser);
	}
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(name = "id") Long id, @RequestBody User user){
		user.setId(id);
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody UserLogindto userLogindto){
		User loggedUser = userService.loginUser(userLogindto.getEmail(), userLogindto.getPassword());
		return ResponseEntity.ok(loggedUser);
	} 
	
}
