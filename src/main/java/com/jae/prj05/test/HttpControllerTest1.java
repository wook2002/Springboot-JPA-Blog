package com.jae.prj05.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// ����� ��û -> ����(HTML ����)
// @Controller


// ����� ��û -> ����(Data��)
// @RestController

@RestController
public class HttpControllerTest1 {
	
//	���ͳ� ������ ��û�� ������ get ��û
//(select) 	http://localhost:8077/http/get
	@GetMapping("/http/get")
	public String getTest() {
		return "get ��û";
//		(200) response : Content-Type: text/html;charset=UTF-8 
	}
	
//(insert)	http://localhost:8077/http/post
	@PostMapping("/http/post")
	public String postTest() {
		return "post ��û";
//		(405) Method Not Allowed
	}
	
//(update)	http://localhost:8077/http/put
	@PutMapping("/http/put")
	public String putTest(@RequestBody String  m) {
		return "put ��û" + m;
	}
	
//(delete)	http://localhost:8077/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete ��û";
	}
}
