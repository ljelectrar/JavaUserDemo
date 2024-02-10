package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.PatchUserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> getUsers() {
		return repository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return repository.findUserById(id);
	}

	@Override
	public void create(User user) {
		repository.save(user);
	}

	@Override
	public void delete(Long id) {
		repository.deleteUserById(id);
	}

	@Override
	public void update(User user, PatchUserRequest request) {
		updateUser(user, request);
		repository.save(user);
	}

	private void updateUser(User user, PatchUserRequest request) {
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
	}

}
