package com.showbuzz.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.showbuzz.dto.Response;
//@RestControllerAdvice
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException ex){
		List<FieldError> error = ex.getFieldErrors();
		class FieldErrorDto{
			private String fieldName;
			private String errorMessage;
			public FieldErrorDto(String fieldName, String errorMessage) {
				this.fieldName = fieldName;
				this.errorMessage = errorMessage;
			}
			public String getFieldName() {
				return fieldName;
			}
			public void setFieldName(String fieldName) {
				this.fieldName = fieldName;
			}
			public String getErrorMessage() {
				return errorMessage;
			}
			public void setErrorMessage(String errorMessage) {
				this.errorMessage = errorMessage;
			}
			@Override
			public String toString() {
				return "FieldErrorDto [fieldName=" + fieldName + ", errorMessage=" + errorMessage + "]";
			}
			
		}
		Stream<Object> result = error.stream().map(err->new FieldErrorDto(err.getField(), err.getDefaultMessage()));
		return Response.error(result);
	}
}
