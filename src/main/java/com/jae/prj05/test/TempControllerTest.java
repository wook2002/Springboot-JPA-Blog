package com.jae.prj05.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// @RestController : return �� ��ü�� Data 
// @Controller : return��ο� �ִ� File
@Controller
public class TempControllerTest {
	
	//http://localhost:8077/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("== tempHome ==");
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		System.out.println("== tempJsp101 ==");
		return "/test1";
	}
} 
