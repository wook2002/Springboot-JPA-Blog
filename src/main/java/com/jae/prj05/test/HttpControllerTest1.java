package com.jae.prj05.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// 사용자 요청 -> 응답(HTML 파일)
// @Controller


// 사용자 요청 -> 응답(Data로)
// @RestController

@RestController
public class HttpControllerTest1 {
	
//	인터넷 브라우저 요청은 무조건 get 요청
//(select) 	http://localhost:8077/http/get
	@GetMapping("/http/get")
	public String getTest() {
		return "get 요청";
//		(200) response : Content-Type: text/html;charset=UTF-8 
	}
	
//(insert)	http://localhost:8077/http/post
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
//		(405) Method Not Allowed
	}
	
//(update)	http://localhost:8077/http/put
	@PutMapping("/http/put")
	public String putTest(@RequestBody String  m) {
		return "put 요청" + m;
	}
	
//(delete)	http://localhost:8077/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
