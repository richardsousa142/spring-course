package com.springcourse.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLogindto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
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
	public ResponseEntity<PageModel<User>> getAllUsers(
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "size", defaultValue = "10") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		
		return ResponseEntity.ok(pm);
	} 
	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> getAllUserRequestsById(
			@PathVariable(name = "id") Long id, 
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "size", defaultValue = "10") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByUserOfRequestIdOnLazyMode(id, pr); 
		return ResponseEntity.ok(pm);
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
