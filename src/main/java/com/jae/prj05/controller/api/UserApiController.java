package com.jae.prj05.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jae.prj05.dto.ResponseDto;
import com.jae.prj05.model.RoleType;
import com.jae.prj05.model.User;
import com.jae.prj05.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired  // @Service로 등록되어 있어서 bean에 있으니 가능
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	
	
	/*
	 *  회원가입 (m1)
	// request 데이터가 json이면 -> @RequestBody
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		// @RequestBody User user
		// username, password, email 
		// => User(id자동으로 들어감, role넣어줘야함, createDate자동으로 들어감)
		
		user.setRole(RoleType.USER);
		System.out.println("UserApiController : save호출됨" + user);
		
		//(1) 바로 200
//		return new ResponseDto<Integer>(200, 1);
		// 클래스: ResponseDto<T>{int status;}
		
		//(2) 좀 바꿔줌
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		// 클래스 : ResponseDto<T>{int status;}
		
		//(3) value() 지워줌 -> 클래스dto 받는 타입 변경시키면 됨.
		
		userService.회원가입(user); // 1 성공, -1 실패
		
		//실제로 DB에 insert하고 아래에서 return이 되면 됨.
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // js객체를 JSON으로 변환해서 리턴 (Jackson)
//		클래스 : ResponseDto<T>{HttpStatus status;}
//		이유 : Enum<E>타입인 HttpStatus
	}
	
	*/
	
	// 로그인 (m1)  , httpSession도 m1, m2
	/*
	@Autowired
	private HttpSession session;
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(
			@RequestBody User user
			, HttpSession session){
		System.out.println("UserApiController : login호출됨" + user);
		User principal = userService.로그인(user); // principal(접근주체)
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	*/
}
