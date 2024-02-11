package com.example.demo.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constants.AppConstants;
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
		validateForPatch(request);
		updateUser(user, request);
		repository.save(user);
	}

	private void validateForPatch(PatchUserRequest request) {
		if (request.getEmail() != null && request.getEmail().isBlank())
			throw new IllegalArgumentException("Email can not be blank");
		
		// Verify regular expressions
		if(request.getEmail() != null && !Pattern.matches(AppConstants.EMAIL_REGEXPR, request.getEmail()))
			throw new IllegalArgumentException("Email is not valid");
		
		if (request.getFirstName() != null && request.getFirstName().isBlank())
			throw new IllegalArgumentException("First name can not be blank");
		
		if (request.getLastName() != null && request.getLastName().isBlank())
			throw new IllegalArgumentException("Last name can not be blank");
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
