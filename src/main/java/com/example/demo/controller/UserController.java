package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PatchUserRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1")
public class UserController {

	@Autowired
	private UserService service;

	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") String userId, 
			@RequestBody PatchUserRequest request)
	{
		// Stream style
		var user = Optional.ofNullable(userId)
				.map(u -> Long.valueOf(userId))
				.map(service::getUser)
				.orElseThrow();
		
		service.update(user, request);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String userId) {
		// Stream style
		var user = Optional.ofNullable(userId)
				.map(u -> Long.valueOf(userId))
				.map(service::getUser)
				.orElseThrow();

		service.delete(user.getUserId());
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody User user) {
		service.create(user);
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return service.getUsers();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") String userId) {
		var user = Optional.ofNullable(userId)
				.map(u -> Long.valueOf(userId))
				.map(service::getUser)
				.orElseThrow();
		
		return user;
	}
}
