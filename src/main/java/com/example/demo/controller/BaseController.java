package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

	@ExceptionHandler({MethodArgumentNotValidException.class, 
		IllegalArgumentException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handleNotValidException() {
		return "this is the method that handles the exception";
	}
}
