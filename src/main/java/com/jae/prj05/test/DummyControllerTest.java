package com.jae.prj05.test;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jae.prj05.model.RoleType;
import com.jae.prj05.model.User;
import com.jae.prj05.repository.UserRepository;

// RestController : data리턴
// Controller : html리턴
@RestController
public class DummyControllerTest {
	
//	컴포넌트 스캔할 때, UserRepository가 메모리에 들어가있는중 => 사용하기만하면 됨
//	DummyControllerTest가 메모리에 들어갈 때, DI 
	@Autowired // 의존성주입(DI)
	private UserRepository userRepository;
	
	
//	{id} url주소 => 파라미터
//	userRepository.getById(id); // jpa => 컬럼에 시퀀스 부분
	// http://localhost:8077/blog/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// url를 변수로 받음(Id부분으로) => 해당하는 컬럼을 가져와야됨
//		User user = userRepository.findById(id)
		
		// (m1) 위험 (절 때 null 없을거니 걍 Optional 빼렴 
//		User user = userRepository.findById(id).get();
		
		// (m2) null이면 너가 넣으렴
		// Optional.orElseGet(Supplier<? extends User> supplier)
//		User user = userRepository.findById(id).orElseGet(supplier)
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
				// 들어가보면 인터페이스 interface는 new못함 -> 익명클래스 필요함
				// DB에 값이 없으면 이게 실행됨
				
				// Supplier타입?
				// 익명객체 - Supplier는 인터페이스여서(인터페이스는 new 로 객체 불가)
//			@Override
//			public User get() {
//				return new User();  //빈객체를 return(빈객체를 user에 넣어줌)
//			}
//		});
		
		// (m3) 가장 많이 씀 (잘못된 인수가 들어오면 throws), 제네릭 머였지
		// CrudRepository
		// @throws IllegalArgumentException if {@literal id} is {@literal null}.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});

		// 람다식 
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당사용자는 없다. id : " + id);  // ; 없으면 에러 
//		});
		
		// Optional<T> findById(ID id);
		// id는 시퀀스로 했던 id임. 
		// null로 올 수도 있으니 Optional로 User객체 감싸서 가져올거임
		// => 직접 null인지 아닌지 판단해야됨.
		
		System.out.println(user);
		
		//RestController -> Data 리턴
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 웹브라우저는 user객체를 이해를 못함 
		//		=> user객체를 변환시켜야됨(json : 웹브라우저가 이해할 수 있는 데이터)
		//		(Gson라이브러리 (java객체 -> json하고 던져줌))
		//		=> 스프링부트 = MessageConverter라는 애가 응답시 자동으로
		//		=> 만약 자바객체를 리턴하면, MessageConver가 Jackson 라이브러리를 호출해서
		//		=> user객체를 json으로 변환시켜 브라우저에게 넘겨줌.
		return user;
	}
	
	// http://localhost:8077/blog/dummy/join
	// http의 body에 usernmae, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(
//				@RequestParam("username")
//				String username, String password, String email  
				User user
			) {
//		System.out.println("username : " + username);
//		System.out.println("password : " + password);
//		System.out.println("email : " + email);
		
		System.out.println("user : " + user);
		System.out.println("username_" + user.getUsername());
		System.out.println("password_" + user.getPassword());
		System.out.println("email_" + user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입완료";
	}
}
