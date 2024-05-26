package com.example.bloggerswebsitebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bloggerswebsitebackend.model.User;
import com.example.bloggerswebsitebackend.repository.UserRepository;
@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
    private UserRepository userRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
        	 return ResponseEntity.ok().body("{\"message\":\"Login successful\"}");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User registerRequest){
    	User user = userRepository.findByUsername(registerRequest.getUsername());
    	if(user == null) {
    		userRepository.save(registerRequest);
    		return ResponseEntity.ok().body("{\"message\":\"Login successful\"}");
    	}
    	return ResponseEntity.status(401).body("User alerady exists");
    }
}
