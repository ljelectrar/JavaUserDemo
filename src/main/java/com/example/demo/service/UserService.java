package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PatchUserRequest;
import com.example.demo.model.User;

public interface UserService {
	public List<User> getUsers();
	public User getUser(Long id);
	public void create(User user);
	public void delete(Long id);
	public void update(User user, PatchUserRequest request);
}
