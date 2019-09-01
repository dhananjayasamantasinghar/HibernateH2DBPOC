package com.h2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h2.model.User;
import com.h2.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping(value="/save",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addUser(@RequestBody User user){
		return new ResponseEntity<>(service.saveUser(user).getName(), HttpStatus.OK);
	}
	
	@GetMapping(value="/getAllUsers",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers(){
		return service.getListOfUsers();
	}
	
	@GetMapping(value="/getUsersByName/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsersByName(@PathVariable(name="name" , required=true) String name){
		return service.getListOfUsers()
						.stream().filter(e->e.getName().contains(name))
						.collect(Collectors.toList());
	}
}
