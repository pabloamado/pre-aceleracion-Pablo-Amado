package com.init.Challenge.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.init.Challenge.exception.CharacterException;
import com.init.Challenge.exception.GenreException;
import com.init.Challenge.exception.MovieException;
import com.init.Challenge.exception.RegisterException;
import com.init.Challenge.exception.msg.ExceptionHandlerMsg;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(value=EntityNotFoundException.class)
	protected ResponseEntity<Object> throwEntityNotFoundException(RuntimeException e, WebRequest webRequest){
		
		String message= ExceptionHandlerMsg.NO_ENTITY + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	@ExceptionHandler(value=NullPointerException.class)
	protected ResponseEntity<Object> throwNullPointerException(RuntimeException e, WebRequest webRequest){
		
		String message= ExceptionHandlerMsg.ACCESS_NULL_OBJECT + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
	@ExceptionHandler(value= {SQLIntegrityConstraintViolationException.class,
			DataIntegrityViolationException.class,ConstraintViolationException.class})
	protected ResponseEntity<Object> throwConstrainViolationException(SQLException e, WebRequest webRequest){
		
		String message= ExceptionHandlerMsg.DB_MSG_CONSTRAIN + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
	@ExceptionHandler(value=GenreException.class)
	protected ResponseEntity<Object> throwGenreException(RuntimeException e, WebRequest webRequest){
		
		String message= ExceptionHandlerMsg.GENRE_ERROR + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
	}
	
	@ExceptionHandler(value=MovieException.class)
	protected ResponseEntity<Object> throwMovieException(RuntimeException e, WebRequest webRequest){
		
		String message= ExceptionHandlerMsg.MOVIE_ERROR + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
	@ExceptionHandler(value=CharacterException.class)
	protected ResponseEntity<Object> throwCharacterException(RuntimeException e, WebRequest webRequest){
		
		String message= ExceptionHandlerMsg.CHARACTER_ERROR + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
	@ExceptionHandler(value=RegisterException.class)
	protected ResponseEntity<Object> throwRegisterException(RuntimeException e, WebRequest webRequest){
		
		String message= ExceptionHandlerMsg.REGISTER_ERROR + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
	
	@ExceptionHandler(value=IOException.class)
	protected ResponseEntity<Object> throwIOException(Exception e, WebRequest webRequest){
		
		String message= ExceptionHandlerMsg.REGISTER_ERROR + e.getMessage();
		
		return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
	}
	
	 @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
	            HttpHeaders headers,
	            HttpStatus status,
	            WebRequest webRequest) {
	        List<String> errors = new ArrayList<>();
	        for (FieldError error : e.getBindingResult().getFieldErrors()) {
	            errors.add(error.getField() + ": " + error.getDefaultMessage());
	        }
	        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
	            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	        }
	        
	        return handleExceptionInternal(e,errors, headers, HttpStatus.BAD_REQUEST, webRequest);
	    }
}
