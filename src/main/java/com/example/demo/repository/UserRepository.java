package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class UserRepository {
	private List<User> users;
	
	public UserRepository() {
		this.users = new ArrayList<User>();
		
		users.add(new User("Leandro", "Junior", "leandro@email.com"));
		users.add(new User("Julia", "Julia", "julia@email.com"));
		users.add(new User("andre", "carrasco", "andre@email.com"));
	}

	public List<User> getUsers() {
		return users;		
	}

	public void save(User user) {
		this.users.add(user);
	}

	public void delete(int id) {
		users.remove(id);
	}

	public void update(String userId, User user) {
		users.set(Integer.valueOf(userId), user);
	}
	
}
