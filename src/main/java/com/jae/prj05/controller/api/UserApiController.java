package com.jae.prj05.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jae.prj05.dto.ResponseDto;
import com.jae.prj05.model.User;

@RestController
public class UserApiController {
	
	// rquest 데이터가 json이면 -> @RequestBody
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save호출됨" + user);
		
		//(1) 바로 200
//		return new ResponseDto<Integer>(200, 1);
		// 클래스: ResponseDto<T>{int status;}
		
		//(2) 좀 바꿔줌
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		// 클래스 : ResponseDto<T>{int status;}

		//(3) value() 지워줌 -> 클래스dto 받는 타입 변경시키면 됨.
		
		//실제로 DB에 insert하고 아래에서 return이 되면 됨.
		return new ResponseDto<Integer>(HttpStatus.OK, 1); // js객체를 JSON으로 변환해서 리턴 (Jackson)
//		클래스 : ResponseDto<T>{HttpStatus status;}
//		이유 : Enum<E>타입인 HttpStatus
	}
}
