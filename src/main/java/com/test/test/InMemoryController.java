package com.test.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InMemoryController {

	@PostMapping("/admin")
	public String saveAdmin() {
		return "Admin saved";
	}
	@GetMapping("/admin")
	public String getAdmin() {
		return "Admin is sent back";
	}
	@PostMapping("/user")
	public String saveUser() {
		return "User saved";
	}
	@GetMapping("/user")
	public String getUser() {
		return "User is sent back";
	}
}