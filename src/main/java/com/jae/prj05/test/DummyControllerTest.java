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
		
//		User user = userRepository.findById(id)
		// (m1) 위험 (절 때 null 없을거니 걍 Optional 빼렴 
//		User user = userRepository.findById(id).get();
		// (m2) null이면 너가 넣으렴
		// Optional.orElseGet(Supplier<? extends User> supplier)
//		User user = userRepository.findById(id).orElseGet(supplier)
		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
		})
		
		// Optional<T> findById(ID id);
		// id는 시퀀스로 했던 id임. 
		// null로 올 수도 있으니 Optional로 User객체 감싸서 가져올거임
		// => 직접 null인지 아닌지 판단해야됨.
		
		
		System.out.println(user);
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
