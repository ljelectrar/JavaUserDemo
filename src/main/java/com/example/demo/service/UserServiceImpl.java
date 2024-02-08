package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PatchUserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> getUsers() {
		return repository.getUsers();
	}

	@Override
	public User getUser(String userId) {
		return repository.getUsers().get(Integer.valueOf(userId));
	}

	@Override
	public void create(User user) {
		repository.save(user);
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}

	@Override
	public void update(String userId, PatchUserRequest request) {
		User user = repository.getUsers().get(Integer.valueOf(userId));

		// update the values that are present in the request
		if (request.getFirstName() != null) {
			user.setFirstName(request.getFirstName());
		}

		if (request.getLastName() != null) {
			user.setLastName(request.getLastName());
		}

		if (request.getEmail() != null) {
			user.setEmail(request.getEmail());
		}
		
		// Save the user 
		repository.update(userId, user);

	}

}
