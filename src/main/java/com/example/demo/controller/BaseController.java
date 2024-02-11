package com.example.demo.controller;

import java.util.NoSuchElementException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.model.ErrorMessage;

@ControllerAdvice
public class BaseController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorMessage> handleNotValidException(MethodArgumentNotValidException e) {
		var errors = e.getAllErrors();

		ErrorMessage message = null;

		if (errors != null && !errors.isEmpty()) {
			message = new ErrorMessage(400, errors.get(0).getDefaultMessage());
			return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
		}

		message = new ErrorMessage(400, "Bad Request");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

}
