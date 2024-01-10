package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {
	
  User registerUser(User user);
  public String login(int id, String name, String password,String email);
List<User> getAll();
User delete(int id);

}
