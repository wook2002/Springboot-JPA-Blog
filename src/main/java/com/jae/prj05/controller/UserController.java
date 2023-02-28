package com.jae.prj05.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jae.prj05.config.auth.PrincipalDetail;

// 인증이 안된 것들 경로(3개)  => (인증안된 사람들 경로를 맨 앞에 붙여줬음)
// 1) /auth/** 
// 2) /(index.jsp)
// 3) static 이하에 있는 ( /js/** , /css/**, /image/** )

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm(
			@AuthenticationPrincipal PrincipalDetail principal  // Authentication객체 가져옴
			) {
		return "user/updateForm";
	}
	
}
