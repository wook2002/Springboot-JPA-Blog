package com.jae.prj05.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(value=IllegalArgumentException.class)
	@ExceptionHandler(value=Exception.class)
	public String handleArgumentException(Exception e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	
//	@ExceptionHandler(value=IllegalArgumentException.class) // ±Ã±ÝÇØ¼­ Ãß°¡ÇØº½
//	public String handleArgumentException(IllegalArgumentException e) {
//		return "<h1>" + e.getMessage() + "</h1><br><h2>IllegalArgumentException</h2>";
//	}
}
