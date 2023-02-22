package com.jae.prj05.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// 사용자 요청 -> 응답(HTML 파일)
// @Controller

import lombok.Builder;


// 사용자 요청 -> 응답(Data로)
// @RestController

@RestController
public class HttpControllerTest1 {
	
	private static final String TAG = "HttpControllerTest1 : ";
	
//	인터넷 브라우저 요청은 무조건 get 요청
//(select) 	http://localhost:8077/blog/http/get
	@GetMapping("/http/get")
	public String getTest(
//			get 값보내는거 => (only) 쿼리스트링
//			m1)
//			@RequestParam int id
//			, @RequestParam String username 
//			m2)
			Member m
			// (온거 자동으로 Member에 들어감)
			// (스프링부트의 MessageConverter가 해줌)
			) {
		m.setId(200);
		System.out.println(TAG + "getter : " + m.getId());
		
//		@Builder테스트
//		Member m = new Member(id, username, password, email)
		Member mTest = Member.builder()
											.username("ssar")
											.password("123")
											.build();
		System.out.println("@Builder테스트 : " + mTest);
//		@AllArgsConstructor는 모든 값 들어간 생성자임.
//		부분적으로 필요할 때마다 생성자를 새로 만들었는데 
//		@builder 사용하면 또 만들 필요 없음.
//		=> 토큰할 때 암호방법들을 이런 식으로 넣었음
		
//		http://localhost:8077/http/get?id=1&username=ssar&password=1234&email=ssar@nate.com
		return "get 요청" + mTest.getId() + ", "+ mTest.getUsername() + mTest.getPassword() + ", " + mTest.getEmail();
//		(200) response : Content-Type: text/html;charset=UTF-8 
	}
	
//(insert)	http://localhost:8077/blog/http/post
	@PostMapping("/http/post")
	public String postTest(
//			1) 쿼리스트링 or
//			2) body (x-www-form-urlencoded) => (걍 <form> 태그)
//			Member m
			
//			3) raw-> text/plain 
//			@RequestBody String text
			
//			4) raw-> application/json
//			 @RequestBody String text 
			@RequestBody Member m 
			// 이러면 자동으로 파싱해서 오브젝트에 넣어줌
			// (온거 자동으로 Member에 들어감)
			// (스프링부트의 MessageConverter가 해줌)
			
			) {
		return "post 요청" + m.getId() + ", "+ m.getUsername() + m.getPassword() + ", " + m.getEmail();
//		return "post 요청" + text;
//		(405) Method Not Allowed
	}
	
//(update)	http://localhost:8077/blog/http/put 
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member  m) {
		return "put 요청" + m ;
	}
	
//(delete)	http://localhost:8077/blog/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}