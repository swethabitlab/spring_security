package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.securityconfig.UserAuthenticationProvider;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;
	@Autowired
	 private UserService userService;
	
	@PostMapping("/user/register")
	public ResponseEntity<User> register(@RequestBody User user){
		
		User user1=userService.registerUser(user);
		user1.setToken(userAuthenticationProvider.createToken(user1.getEmail()));
		System.out.println(" jwt token whlie register "+user1.getToken());
		return new ResponseEntity<User>(user1,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<String> loginpage(@PathVariable int id,@RequestBody Map<String, String> request)
	{
		String name=request.get("name");
		String password=request.get("password");
		String email=request.get("email");
		return new ResponseEntity<String>(userService.login(id, name, password,email),HttpStatus.OK);
	}
	@GetMapping("/user/get")
	public ResponseEntity<List<User>> retriveAll(){
		return new ResponseEntity<List<User>>(userService.getAll(),HttpStatus.OK);
	}
	
	public ResponseEntity<User> deleteid(@PathVariable int id){
		return new ResponseEntity<User>(userService.delete(id),HttpStatus.OK);
	}
}
