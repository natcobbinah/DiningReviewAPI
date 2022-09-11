package com.example.DiningReviewApi.ExceptionHandlers;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends RuntimeException {
	
	public AlreadyExistException(String message) {
		super(message);
	}
	
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
