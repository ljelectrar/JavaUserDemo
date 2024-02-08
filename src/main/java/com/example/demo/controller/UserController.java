package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PatchUserRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("v1")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/test")
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> test(@RequestHeader(value = "number1", defaultValue="100") int num) {
		return new ResponseEntity<>("This is a String" + num, HttpStatus.OK);
	}
	
	/*
	@GetMapping("/test")
	public String test(@RequestHeader HttpHeaders header) {
		return header.getHost().getHostName() + " -  " + header.getHost().getPort();
	}

	@GetMapping("/test")
	public String test2(@RequestHeader("number") int number) {
		return "" + number;
	}
	
	@GetMapping("/test")
	public void test3(@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			System.out.println(key + " - " + value);
		})
	}
	*/

	/*
	 * @GetMapping("/test/{id}/{name}") public String test(@PathVariable int
	 * id, @PathVariable String name) { return id + " " + name; }
	 * 
	 * //http://localhost:8080/test?id=2&name=Ana
	 * 
	 * @GetMapping("/test}") public void test2(@RequestParam("id") int
	 * id, @RequestParam("name" String name) { System.out.println(id + " " + name);
	 * }
	 * 
	 */

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String userId) {
		service.delete(Integer.valueOf(userId));
	}

	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") String userId, @RequestBody PatchUserRequest request) {
		service.update(userId, request);
	}

	@PostMapping("/create")
	public void create(@RequestBody User user) {
		service.create(user);
	}

	@GetMapping("/users")
	public List<User> sayHello() {
		return service.getUsers();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") String userId) {
		return service.getUser(userId);
	}

}
