package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@CrossOrigin("*")
@RequestMapping("/admin/")
public class adminController {
	
	private static final Logger logger = Logger.getLogger(adminController.class);
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String Home(Model model) {
		 model.addAttribute("user", userService.getAllUsers());
		return "adminDashboard";
	}
	
	 @PostMapping("/customers")
	    public String saveCustomers(@ModelAttribute("user") User customer) {
	        try {
	            User user = new User();
	            user.setRole("ROLE_USER");
	            userService.saveUser(customer);

	            // Log an INFO message for successfully saving the customer
	            logger.info("Saved a new customer with email: " + customer.getEmail());

	            return "redirect:/admin/";
	        } catch (Exception e) {
	            // Log the exception with an ERROR level
	            logger.error("An error occurred while saving a new customer", e);

	            // Log a WARN message for the save failure
	            logger.warn("Failed to save a new customer");

	            return "redirect:/errorPage";
	        }
	    }
	
	
	@GetMapping("/users/edit/{id}")
	public String editAdmin(@PathVariable Long id, Model model) {
	    User user = userService.getUserById(id);
	    model.addAttribute("user", user);
	    return "editUser";
	}


	@PostMapping("/users/edit/{id}")
	public String updateAdmin(@PathVariable Long id, @ModelAttribute("user") User admin, Model model) {
	    User existingAdmin = userService.getUserById(id);
	    existingAdmin.setId(id);
	    existingAdmin.setName(admin.getName());
	    existingAdmin.setAge(admin.getAge());
	    existingAdmin.setUserName(admin.getUserName());
	    existingAdmin.setEmail(admin.getEmail());
	    existingAdmin.setPassword(admin.getPassword());
	    existingAdmin.setMobile(admin.getMobile());

	    userService.updateUser(existingAdmin);
	    return "redirect:/admin/";
	}

	
	@GetMapping("/users/{id}")
    public String deleteProduct(@PathVariable Long id) {
        try {
            userService.deleteUserByID(id);

            // Log an INFO message for successfully deleting a user
            logger.info("Deleted user with ID: " + id);

            return "redirect:/admin/";
        } catch (Exception e) {
            // Log the exception with an ERROR level
            logger.error("An error occurred while deleting user with ID: " + id, e);

            // Log a WARN message for the delete failure
            logger.warn("Failed to delete user with ID: " + id);

            return "redirect:/errorPage";
        }
    }
}
