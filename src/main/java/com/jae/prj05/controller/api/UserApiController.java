package com.jae.prj05.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jae.prj05.config.auth.PrincipalDetail;
import com.jae.prj05.dto.ResponseDto;
import com.jae.prj05.model.User;
import com.jae.prj05.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired  // @Service로 등록되어 있어서 bean에 있으니 가능
	private UserService userService;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/user")  // @RequestBody : json받기 <-> key=value(x-www-form-urlencoded)
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.회원수정(user);
		// 트랜젝션이 종료돼서 더티체킹으로 DB값은 변경 됨.
		// but session값은 변경되지 않음(수정페이지 들어가면 변경이 안돼 있음)
		
		// 세션값 강제 변경 (시큐리티는 좀 까다로움)
		// org.springframework.security.core.Authentication
		// principal, credentials
		
		// 강제로 세션값 변경
		// (x)
//		Authentication authentication =
//				new UsernamePasswordAuthenticationToken
//							(principal, null, principal.getAuthorities());
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		securityContext.setAuthentication(authentication);
//		session.setAttribute("SPRING_SECURITY_CONTEXTA", securityContext);
		
//		Authentication authentication 
//		= authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken
//					(user.getUsername(), user.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
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
