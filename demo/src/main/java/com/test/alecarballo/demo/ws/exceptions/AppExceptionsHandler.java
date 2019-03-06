package com.test.alecarballo.demo.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.test.alecarballo.demo.controller.model.response.ErrorMessage;



@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handlyAnyException (Exception ex, WebRequest request){
		
		String errroMessageDescription = ex.getLocalizedMessage();
		if(errroMessageDescription == null)errroMessageDescription = ex.toString();
		
		
		ErrorMessage errormessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(errormessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ExceptionHandler(value= {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException (NullPointerException ex, WebRequest request){
		
		String errroMessageDescription = ex.getLocalizedMessage();
		if(errroMessageDescription == null)errroMessageDescription = ex.toString();
		
		
		ErrorMessage errormessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(errormessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
