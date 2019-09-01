package com.h2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2.model.User;
import com.h2.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	public User saveUser(User user){
		return repo.save(user);
	}
	
	public List<User> getListOfUsers(){
		return repo.findAll();
	}
}
