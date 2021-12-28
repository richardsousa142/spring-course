package com.springcourse.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.RequestService;



/**
 *Classe para verificar se o usuario é o mesmo que ira tentar efetuar alguma alteração
 *compara os tokens 
 */
@Component("accessManager")
public class AccessManager {
	
	@Autowired UserRepository userRepository;
	@Autowired RequestService requestService;
	
	public boolean isOwner(Long id) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> userResult =  userRepository.findByEmail(email);
		
		if(!userResult.isPresent()) throw new NotFoundException("There are not user with email = " + email);
			
		User user = userResult.get();
		return user.getId() == id;
	}
	
	public boolean isRequestOwner(Long id) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> userResult =  userRepository.findByEmail(email);
		
		if(!userResult.isPresent()) throw new NotFoundException("There are not user with email = " + email);
			
		User user = userResult.get();
		Request request = requestService.getRequestById(id);
		return user.getId() == request.getUserOfRequest().getId();
	}
}
