package com.springcourse.resource;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginResponsedto;
import com.springcourse.dto.UserLogindto;
import com.springcourse.dto.UserSavedto;
import com.springcourse.dto.UserUpdateRoledto;
import com.springcourse.dto.UserUpdatedto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.security.JwtManager;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

@RestController
@RequestMapping(value="users")
public class UserResource {
	@Autowired private UserService userService;
	@Autowired private RequestService requestService;
	@Autowired private AuthenticationManager authManager;
	@Autowired private JwtManager jwtManager;
	
	@Secured(value  = { "ROLE_ADMINISTRATOR" })
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserSavedto userdto){
		User userToSave = userdto.transformaToUser();
		User createdUser = userService.saveUser(userToSave);
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
	
	@PreAuthorize("@accessManager.isOwner(#id)")
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdatedto userdto){
		User user = userdto.transformaToUser();
		user.setId(id);
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponsedto> loginUser(@RequestBody @Valid UserLogindto userLogindto){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLogindto.getEmail(), userLogindto.getPassword());
		Authentication auth =  authManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		org.springframework.security.core.userdetails.User userSpring = 
								(org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities()
								.stream()
								.map(authority -> authority.getAuthority())
								.collect(Collectors.toList());		
		
		return ResponseEntity.ok(jwtManager.creteToken(email, roles));
	} 
	
	@Secured({ "ROLE_ADMINISTRATOR" })
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRoleOfUser(@PathVariable(name = "id") Long id, @RequestBody @Valid UserUpdateRoledto userUpdateRole){
		User user = new User();
		System.out.println(userUpdateRole.toString()); 
		user.setId(id);
		user.setRoleOfUser(userUpdateRole.getRoleOfUser());
		userService.updateRoleOfUser(user);
		return ResponseEntity.ok().build();
	}
}   
 