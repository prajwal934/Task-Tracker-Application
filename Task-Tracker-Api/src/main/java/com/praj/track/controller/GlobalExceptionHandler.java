package com.praj.track.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.praj.track.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<ErrorResponse> handleExceptions(
			RuntimeException ex , WebRequest request
			) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
