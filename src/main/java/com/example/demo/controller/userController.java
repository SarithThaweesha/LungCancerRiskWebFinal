package com.example.demo.controller;

import java.security.Principal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.User;


@Controller
@CrossOrigin("*")
@RequestMapping("/user/")
public class userController {
	@Autowired
	private com.example.demo.repository.UserRepo userRepo;

	private static final Logger logger = Logger.getLogger(adminController.class);

	@GetMapping("/")
	public String Home(Model model, Principal principal) {
		boolean isLoggedIn = principal != null;

		model.addAttribute("isLoggedIn", isLoggedIn);

		if (isLoggedIn) {
			String email = principal.getName();
			User user = userRepo.findByEmail(email);
			model.addAttribute("user", user);
		}
		return "manageAccount";
	}

}
