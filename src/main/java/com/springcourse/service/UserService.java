package com.springcourse.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.util.HashUtil;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	public  User saveUser(User user) {
		String encryptedPassword = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(encryptedPassword);
		User createdUser = userRepository.save(user);
		return createdUser;
	}
	public User updateUser(User user) {
		String encryptedPassword = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(encryptedPassword);
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	public User getUserById(Long id) {
		Optional<User> resultUser = userRepository.findById(id);
		return resultUser.orElseThrow(() -> new NotFoundException("There are not user with id = "+id));
	}
	public List<User> listAllUsers(){
		List<User> listWithAllUsers = userRepository.findAll();
		return listWithAllUsers;
	}
	public PageModel<User> listAllOnLazyMode(PageRequestModel pageRequest){
		Pageable pageAble = PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
		Page<User> page = userRepository.findAll(pageAble);
		PageModel<User> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm; 
	}
	public User loginUser(String email, String password) {
		password = HashUtil.getSecureHash(password);
		Optional<User> resultUserLogin = userRepository.login(email, password);
		return resultUserLogin.get();
	}
	public int updateRoleOfUser(User user) {
		return userRepository.updateRoleOfUser(user.getId(), user.getRoleOfUser().toString());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findByEmail(username);
		if(!result.isPresent()) throw new UsernameNotFoundException("Usuario com com email: "+username+" não existe.");
		User user = result.get();
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE "+user.getRoleOfUser().name()));
		
		org.springframework.security.core.userdetails.User userSpring = 
				new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		
		return userSpring;
	}
	
}
