package com.manageuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
	@ExceptionHandler(value = {Exception.class, java.lang.Exception.class})
	public ResponseEntity<Object> exception(Exception exception) {
	      return new ResponseEntity<>("There is an unhandled exception occurred part of the program execution, view the log file for details", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
