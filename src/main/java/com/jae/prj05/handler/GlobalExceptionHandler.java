package com.jae.prj05.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.jae.prj05.dto.ResponseDto;

@ControllerAdvice  //aop방식
@RestController
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(value=IllegalArgumentException.class)
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
//		return "<h1>" + e.getMessage() + "</h1>";
	}
	
//	@ExceptionHandler(value=IllegalArgumentException.class) // 테스트
//	public String handleArgumentException(IllegalArgumentException e) {
//		return "<h1>" + e.getMessage() + "</h1><br><h2>IllegalArgumentException</h2>";
//	}
}
