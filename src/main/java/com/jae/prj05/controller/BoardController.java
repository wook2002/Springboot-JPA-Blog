package com.jae.prj05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jae.prj05.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	// 이건 안됨(겪어봄 기억안남)
//	@Autowired
//	private PrincipalDetail principal;
	
//	@GetMapping({"/",""})
//	public String index(
//			// 컨트롤러에서 세션을 어떻게 찾는지
//			// org.springframework.security.core.annotation.AuthenticationPrincipal;
//			@AuthenticationPrincipal PrincipalDetail principal
//			) {
//		System.out.println("로그인 아이디 : " + principal.getUsername());
//		return "index";
//	}
	
	@GetMapping({"/",""})
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}
