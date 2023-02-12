package com.jae.prj05.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// ����� ��û -> ����(HTML ����)
// @Controller

import lombok.Builder;


// ����� ��û -> ����(Data��)
// @RestController

@RestController
public class HttpControllerTest1 {
	
	private static final String TAG = "HttpControllerTest1 : ";
	
//	���ͳ� ������ ��û�� ������ get ��û
//(select) 	http://localhost:8077/http/get
	@GetMapping("/http/get")
	public String getTest(
//			get �������°� => (only) ������Ʈ��
//			m1)
//			@RequestParam int id
//			, @RequestParam String username 
//			m2)
			Member m
			// (�°� �ڵ����� Member�� ��)
			// (��������Ʈ�� MessageConverter�� ����)
			) {
		m.setId(200);
		System.out.println(TAG + "getter : " + m.getId());
		
//		@Builder�׽�Ʈ
//		Member m = new Member(id, username, password, email)
		Member mTest = Member.builder()
											.username("ssar")
											.password("123")
											.build();
		System.out.println("@Builder�׽�Ʈ : " + mTest);
//		@AllArgsConstructor�� ��� �� �� ��������.
//		�κ������� �ʿ��� ������ �����ڸ� ���� ������µ� 
//		@builder ����ϸ� �� ���� �ʿ� ����.
//		=> ��ū�� �� ��ȣ������� �̷� ������ �־���
		
//		http://localhost:8077/http/get?id=1&username=ssar&password=1234&email=ssar@nate.com
		return "get ��û" + mTest.getId() + ", "+ mTest.getUsername() + mTest.getPassword() + ", " + mTest.getEmail();
//		(200) response : Content-Type: text/html;charset=UTF-8 
	}
	
//(insert)	http://localhost:8077/http/post
	@PostMapping("/http/post")
	public String postTest(
//			1) ������Ʈ�� or
//			2) body (x-www-form-urlencoded) => (�� <form> �±�)
//			Member m
			
//			3) raw-> text/plain 
//			@RequestBody String text
			
//			4) raw-> application/json
//			 @RequestBody String text 
			@RequestBody Member m 
			// �̷��� �ڵ����� �Ľ��ؼ� ������Ʈ�� �־���
			// (�°� �ڵ����� Member�� ��)
			// (��������Ʈ�� MessageConverter�� ����)
			
			) {
		return "post ��û" + m.getId() + ", "+ m.getUsername() + m.getPassword() + ", " + m.getEmail();
//		return "post ��û" + text;
//		(405) Method Not Allowed
	}
	
//(update)	http://localhost:8077/http/put 
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member  m) {
		return "put ��û" + m ;
	}
	
//(delete)	http://localhost:8077/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete ��û";
	}
}
