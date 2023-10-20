package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@CrossOrigin("*")
public class frontendController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	@GetMapping("/")
	public String Home() {
		return "home";
	}
	
	@GetMapping("/login")
	public String lgoin() {
		return "login";
	}
	
	@GetMapping("/register")
	public String tegister() {
		return "register";
	}
	
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		User existingUser = userRepo.findByEmail(user.getEmail());

		if (existingUser != null) {
			session.setAttribute("msgError", "Email address already exists. Please use a different email.");
			session.removeAttribute("msg");
			return "redirect:/login";
		} else {
			User savedUser = userService.saveUser(user);

		}
		return "redirect:/login";
	}
	
}
