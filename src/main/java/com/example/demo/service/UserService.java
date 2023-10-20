package com.example.demo.service;

import java.util.List;
import com.example.demo.model.User;

public interface UserService {
	public User saveUser(User user);

	public void removeSessionMessage();
	
	List<User> getAllUsers();
	
	void deleteUserByID(Long id);
	
	User getUserById(Long id);
	User updateUser(User admin);
}
