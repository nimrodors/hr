package hu.webuni.hr.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> handleValidationError(MethodArgumentNotValidException e, WebRequest r) {
		BindingResult bindingResult = e.getBindingResult();
		MyError myError = null;
		for (FieldError f : bindingResult.getFieldErrors()) {
			myError = new MyError(f.getDefaultMessage().toUpperCase());
			
		}
		for (ObjectError o: bindingResult.getGlobalErrors()) {
			o.getDefaultMessage();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
	}
}
