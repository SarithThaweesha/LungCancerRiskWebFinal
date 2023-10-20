package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
		User newUser= userRepo.save(user);
		return newUser;
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session= ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
		session.removeAttribute("msgError");
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public void deleteUserByID(Long id) {
		userRepo.deleteById(id);
		
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}

	@Override
	public User updateUser(User admin) {
		// TODO Auto-generated method stub
		return userRepo.save(admin);
	}
}
