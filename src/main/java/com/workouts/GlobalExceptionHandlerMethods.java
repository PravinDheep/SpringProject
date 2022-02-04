package com.workouts;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerMethods {
	
	@ExceptionHandler(value=NullPointerException.class)
	public String handleNullPointerException(Exception e) {
		System.out.println("Null Pointer exception occured: " + e);
		return "Null Pointer Exception"; //returning view name.
	}
	

	@ExceptionHandler(value=IOException.class)
	public String handleIOException(Exception e) {
		System.out.println("IOException occured: " + e);
		return "IOException"; //returning view name.
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public String handleException(Exception e) {
		System.out.println("Exception occured: " + e);
		return "Exception"; //returning view name.
	}
}