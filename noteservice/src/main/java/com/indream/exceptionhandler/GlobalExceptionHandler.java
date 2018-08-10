package com.indream.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.indream.noteservice.model.NoteResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = UserException.class)
	public ResponseEntity<NoteResponse> getException(UserException e) {

		NoteResponse response = new NoteResponse(e.getMessage(), 5001);
		return new ResponseEntity<NoteResponse>(response, HttpStatus.CONFLICT);

	}

	@ExceptionHandler(value = TokenException.class)
	public ResponseEntity<NoteResponse> getException(TokenException e) {

		NoteResponse response = new NoteResponse(e.getMessage(), 6001);
		return new ResponseEntity<NoteResponse>(response, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<NoteResponse> getException(Exception e) {

		NoteResponse response = new NoteResponse(e.getMessage(), 7001);
		return new ResponseEntity<NoteResponse>(response, HttpStatus.NOT_ACCEPTABLE);

	}

	@ExceptionHandler(value = GenericException.class)
	public ResponseEntity<NoteResponse> getException(GenericException e) {

		NoteResponse response = new NoteResponse(e.getMessage(), 1001);
		return new ResponseEntity<NoteResponse>(response, HttpStatus.BAD_REQUEST);

	}
}
