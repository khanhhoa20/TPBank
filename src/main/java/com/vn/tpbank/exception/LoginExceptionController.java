package com.vn.tpbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Khánh Hòa
 *
 */
@ControllerAdvice
public class LoginExceptionController {
	
	@ExceptionHandler(value = LoginFailException.class)
	public ResponseEntity<Object> exception(LoginFailException exception) {
		return new ResponseEntity<>("Login Fail. Wrong username or password!", HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value = LoginException.class)
	public ResponseEntity<CustomErrorResponse> loginException(Exception ex, WebRequest webRequest) {
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setError(ex.getMessage());
		customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<CustomErrorResponse>(customErrorResponse,HttpStatus.NOT_FOUND);
	}
}
