package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
@Service
public class UserImplementation implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User use=new User();
				use.setName(user.getName());
				use.setPassword(user.getPassword());
				use.setEmail(user.getEmail());
		return userRepository.save(user);
		 
	}

	@Override
	public String login(int id, String name, String password,String email) {
		if(userRepository.existsById(id)) {
			userRepository.findById(id);
			return "login successfully";
		}
		return "id not registered";
	}

	@Override
	public List<User> getAll() {
		
		return userRepository.findAll();
	}

	@Override
	public User delete(int id) {
		userRepository.deleteById(id);
		return null;
	}

	
	
	

	
	

}
