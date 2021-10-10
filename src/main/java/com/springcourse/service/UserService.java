package com.springcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.util.HashUtil;

@Service
public class UserService {
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
	public User loginUser(String email, String password) {
		password = HashUtil.getSecureHash(password);
		Optional<User> resultUserLogin = userRepository.login(email, password);
		return resultUserLogin.get();
	}
}
