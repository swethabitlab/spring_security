package com.bitlabs.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.Dto.AuthRequestDTO;
import com.bitlabs.Dto.JwtResponseDTO;
import com.bitlabs.entity.UserInfo;
import com.bitlabs.entity.UserRole;
import com.bitlabs.repository.UserRepository;
import com.bitlabs.service.JwtService;

@RestController
public class AuthContoller {
@Autowired 
private JwtService jwtService;

@Autowired
private UserRepository userInfoRepository; // Assuming you have a repository for User

@Autowired
private AuthenticationManager authenticationManager;

@Autowired
private PasswordEncoder passwordEncoder;
	
	@PostMapping("/api/v1/login")
	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
	
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
	    if(authentication.isAuthenticated()){
	       return  JwtResponseDTO.builder().accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
		    } else {
	        throw new UsernameNotFoundException("invalid user request..!!");
	    }
	}
	
	
	

	    @PostMapping("/v1/register")
	    public ResponseEntity<String> registerUser(@RequestBody AuthRequestDTO registrationRequest) {
	        // Check if the username is already taken
	        if (userInfoRepository.existsByUsername(registrationRequest.getUsername())) {
	            return ResponseEntity.badRequest().body("Username is already taken.");
	        }

	        
	        
	     // Create a new user
	        UserInfo newUser = new UserInfo();
	        newUser.setUsername(registrationRequest.getUsername());
	        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

	        // Save the new user
	        userInfoRepository.save(newUser);

	        return ResponseEntity.ok("User registered successfully.");
	    }
	    
	    
	    
	   
	    @GetMapping("/api/v1/ping")
	    public String test() {
	        try {
	            return "Welcome";
	        } catch (Exception e){
	            throw new RuntimeException(e);
	        }
	    } 
	}

