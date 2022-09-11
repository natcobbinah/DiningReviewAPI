package com.example.DiningReviewApi.ExceptionHandlers;


import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDTO> generateNotFoundException(NotFoundException nfex){
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(nfex.getMessage());
		errorDTO.setStatus(String.valueOf(nfex.getStatus().value()));
		errorDTO.setTime(new Date().toString());
		
		return new ResponseEntity<ErrorDTO>(errorDTO, nfex.getStatus());
	}
	
	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<ErrorDTO> generateAlreadyExistsException(AlreadyExistException alex){
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setMessage(alex.getMessage());
		errorDTO.setStatus(String.valueOf(alex.getStatus().value()));
		errorDTO.setTime(new Date().toString());
		
		return new ResponseEntity<ErrorDTO>(errorDTO, alex.getStatus());
	}
}
