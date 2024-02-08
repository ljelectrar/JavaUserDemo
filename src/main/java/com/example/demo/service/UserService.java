package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PatchUserRequest;
import com.example.demo.model.User;

public interface UserService {
	public List<User> getUsers();
	public User getUser(String userId);
	public void create(User user);
	public void delete(Integer id);
	public void update(String userId, PatchUserRequest request);
}
