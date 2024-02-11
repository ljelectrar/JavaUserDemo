package com.example.demo.controller;

import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.model.ErrorMessage;

public class BaseController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleNotValidException(MethodArgumentNotValidException e) {
		var errors = e.getAllErrors();
		if (errors != null && !errors.isEmpty())
			return new ErrorMessage(400, errors.get(0).getDefaultMessage());
		
		return  new ErrorMessage(400,"Bad Request");
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleConstraints(Exception e) {
		
		return  new ErrorMessage(400,"Bad Request");
	}
	
	@ExceptionHandler({NoSuchElementException.class, NumberFormatException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleNoSuchElement(Exception e) {
		
		return  new ErrorMessage(404,"Not Found");
	}
}
