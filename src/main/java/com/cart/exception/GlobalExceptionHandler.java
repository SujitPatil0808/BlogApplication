package com.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cart.constants.AppConstants;
import com.cart.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
			
		ApiResponse api=new ApiResponse();
		api.setMessage(AppConstants.NOT_FOUND);
		api.setStatus(true);
		api.setHttpStatus(HttpStatus.NOT_FOUND);
		
		
		return new ResponseEntity<ApiResponse>(api,HttpStatus.OK);
		
	}

}
