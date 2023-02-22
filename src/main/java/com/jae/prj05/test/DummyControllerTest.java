package com.jae.prj05.test;
import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();   // List<T> findAll();
	}
	
//	Delete
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		// null값 오면 오류( deleteById-> void)
//		userRepository.deleteById(id); 
		
		try {
			userRepository.deleteById(id);
		}catch(IllegalArgumentException e) {
//		}catch(EmptyResultDataAccessException e) {
//		}catch(Exception e) { // 요건 귀찮으면(but 다른 Exception일 수도 있으니) 
			return "삭제 실패 해당 id 없음";
		}
		return "삭제완료 id : " + id;
	}
	
//	 ------- Pageable -------
	// http://localhost:8077/blog/dummy/user?page=0
	//data.domain Pageable 
	// 한페이지당 2건 (스프링부트+JPA -> 페이징)
	@GetMapping("/dummy/user")
	public List<User> pageList(  		//public interface Page<T> extends Slice<T> {
			@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC)
			Pageable pageable){
		
		//(m1)
//		  Page<User> users = 	userRepository.findAll(pageable); // Page<T> findAll(Pageable pageable);
		 // content[], pageable{}, last 등등 다 나옴 
		 // => return Page<User> // List로 던지는게 좋을듯
		 
		//(m2)
//	     List<User> users = 	userRepository.findAll(pageable).getContent();
	    
		//(m3)
//	    Page<User> pagingUser = 	userRepository.findAll(pageable);
//	    List<User> users = pagingUser.getContent();
		
		//(m3-1)
		Page<User> pagingUser = 	userRepository.findAll(pageable);
//		if(pagingUser.isFirst())
//		if(pagingUser.isLast())
		// 등등 '분기'처리가능
	    List<User> users = pagingUser.getContent();
	    
		return users;
	}
	
	
// ------- Update (Put)------- (Put이라 Get이랑 url 겹쳐도 구분가능)
	@Transactional // //(m2) javax.transaction
	@PutMapping("/dummy/user/{id}")
	public User updateUser(
				@PathVariable int id
				
//				, User requestUser  
				// 이건 -> form태그만 받을 수 있음
				
				,@RequestBody User requestUser 
				// json 받기
				// springBoot의 MessageConvert의 Jackson라이브러리가
				// 변환해줌 (json -> java객체) -> 이게 바로 @RequestBody이라는 어노테이션
			) {
		
//		System.out.println("id : " + id);
//		System.out.println("requestUser : " + requestUser);
		// requestUser : User(id=0, username=null, password=수정, email=updat1@따라.컴, role=null, createDate=null)
//		System.out.println("password : " + requestUser.getPassword());
//		System.out.println("email : " + requestUser.getEmail());
		
	//requestUser.setId(id);
	//userRepository.save(requestUser);
		// ---> update(m1-1)
		//.save() 좀더 자세히
		// 그냥 덮어버림(data같은거 null 되거나, notNoll 있으면 오류나고...))
		// (
		//      id 전달(x) => insert
		// 	 id 전달(o) + 해당데이터(x) => insert
		// 	 id 전달(o) + 해당데이터(o) => update
		//)
		
		// update((m1) 데이터가져와서 덮어씌움)
		// !!! Java는 파라미터(Arg)에 함수가 못들어감 ! => 람다로 가능(java8에서 생겨서)
		// (Parameter(변수명) vs Argment(값)) => 그래서 IllegalArgumentException
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//(m1)  .save()
//		userRepository.save(user);
		
		//(m2) @Transactional
		// 더티 체킹(Dirty Checking)
		
		// (JPA)영속성 컨텍스트(Persistence Context), flush
		
		return user;
	}
	
// ------- Detail (Get)-------  (Put이라 Get이랑 url 겹쳐도 구분가능)
// -------	{id} url주소 => 파라미터 -------
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
		
		// (m3) 가장 많이 씀 (잘못된 인수가 들어오면 throws)
		// CrudRepository
		// @throws IllegalArgumentException if {@literal id} is {@literal null}.
		// Supplier는 인터페이스 => new(x) => new 익명클래스 
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
		// user 객체 = 자바 오브젝트
		// 요청 : 웹브라우저
		
		// => (자바객체 -> data 변환해야되는데...) 아무튼 그래서
		
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