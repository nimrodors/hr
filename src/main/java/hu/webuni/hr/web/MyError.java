package hu.webuni.hr.web;

import java.util.List;

import org.springframework.validation.FieldError;

public class MyError {
	
	private String message;
	private int errorCode;
	private List<FieldError> fieldErrors;
	
	public MyError(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public MyError(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	} 
	
}
