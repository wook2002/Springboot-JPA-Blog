package com.jae.prj05.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 메모리에 new로 올리는거 같은것(스프링 컨테이너가 관리) -> IoC  
public class TestController {
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello sprin</h1>";
	}
	
// 스프링 IoC(제어의역전) - 직접 new로 메모리에 올리지 마
//		=> '싱글톤' -> 레퍼런스 변수를 스프링이 관리

//		후에 정리좀 할것들...
//		(지역변수에서) => new는 heap에 올라감(변수는 주소공간의 주소명)
//		stack의 모든 객체는 지역변수가 관리? 
//		=> new로 heap에 계속 올리는거 너가 하지말고
//			spring이 대신 하는 하겠음(IoC)
//	 	스프링 Scan 설명하기위해 설명한 이런저런것들임. 
//		https://www.youtube.com/watch?v=n33ao_cbhsU&list=PL93mKxaRDidECgjOBjPgI3Dyo8ka6Ilqm&index=6
	
	
}