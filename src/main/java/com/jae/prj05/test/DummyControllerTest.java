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

// RestController : data����
// Controller : html����
@RestController
public class DummyControllerTest {
	

//	������Ʈ ��ĵ�� ��, UserRepository�� �޸𸮿� ���ִ��� => ����ϱ⸸�ϸ� ��
//	DummyControllerTest�� �޸𸮿� �� ��, DI 
	@Autowired // ����������(DI)
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();   // List<T> findAll();
	}
	
//	Delete
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		// null�� ���� ����( deleteById-> void)
//		userRepository.deleteById(id); 
		
		try {
			userRepository.deleteById(id);
		}catch(IllegalArgumentException e) {
//		}catch(EmptyResultDataAccessException e) {
//		}catch(Exception e) { // ��� ��������(but �ٸ� Exception�� ���� ������) 
			return "���� ���� �ش� id ����";
		}
		return "�����Ϸ� id : " + id;
	}
	
//	 ------- Pageable -------
	// http://localhost:8077/blog/dummy/user?page=0
	//data.domain Pageable 
	// ���������� 2�� (��������Ʈ+JPA -> ����¡)
	@GetMapping("/dummy/user")
	public List<User> pageList(  		//public interface Page<T> extends Slice<T> {
			@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC)
			Pageable pageable){
		
		//(m1)
//		  Page<User> users = 	userRepository.findAll(pageable); // Page<T> findAll(Pageable pageable);
		 // content[], pageable{}, last ��� �� ���� 
		 // => return Page<User> // List�� �����°� ������
		 
		//(m2)
//	     List<User> users = 	userRepository.findAll(pageable).getContent();
	    
		//(m3)
//	    Page<User> pagingUser = 	userRepository.findAll(pageable);
//	    List<User> users = pagingUser.getContent();
		
		//(m3-1)
		Page<User> pagingUser = 	userRepository.findAll(pageable);
//		if(pagingUser.isFirst())
//		if(pagingUser.isLast())
		// ��� '�б�'ó������
	    List<User> users = pagingUser.getContent();
	    
		return users;
	}
	
	
// ------- Update (Put)------- (Put�̶� Get�̶� url ���ĵ� ���а���)
	@Transactional // //(m2) javax.transaction
	@PutMapping("/dummy/user/{id}")
	public User updateUser(
				@PathVariable int id
				
//				, User requestUser  
				// �̰� -> form�±׸� ���� �� ����
				
				,@RequestBody User requestUser 
				// json �ޱ�
				// springBoot�� MessageConvert�� Jackson���̺귯����
				// ��ȯ���� (json -> java��ü) -> �̰� �ٷ� @RequestBody�̶�� ������̼�
			) {
		
//		System.out.println("id : " + id);
//		System.out.println("requestUser : " + requestUser);
		// requestUser : User(id=0, username=null, password=����, email=updat1@����.��, role=null, createDate=null)
//		System.out.println("password : " + requestUser.getPassword());
//		System.out.println("email : " + requestUser.getEmail());
		
	//requestUser.setId(id);
	//userRepository.save(requestUser);
		// ---> update(m1-1)
		//.save() ���� �ڼ���
		// �׳� �������(data������ null �ǰų�, notNoll ������ ��������...))
		// (
		//      id ����(x) => insert
		// 	 id ����(o) + �ش絥����(x) => insert
		// 	 id ����(o) + �ش絥����(o) => update
		//)
		
		// update((m1) �����Ͱ����ͼ� �����)
		// !!! Java�� �Ķ����(Arg)�� �Լ��� ���� ! => ���ٷ� ����(java8���� ���ܼ�)
		// (Parameter(������) vs Argment(��)) => �׷��� IllegalArgumentException
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("������ ����");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//(m1)  .save()
//		userRepository.save(user);
		
		//(m2) @Transactional
		// ��Ƽ üŷ(Dirty Checking)
		
		// (JPA)���Ӽ� ���ؽ�Ʈ(Persistence Context), flush
		
		return user;
	}
	
// ------- Detail (Get)-------  (Put�̶� Get�̶� url ���ĵ� ���а���)
// -------	{id} url�ּ� => �Ķ���� -------
//	userRepository.getById(id); // jpa => �÷��� ������ �κ�
	// http://localhost:8077/blog/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// url�� ������ ����(Id�κ�����) => �ش��ϴ� �÷��� �����;ߵ�
//		User user = userRepository.findById(id)
		
		// (m1) ���� (�� �� null �����Ŵ� �� Optional ���� 
//		User user = userRepository.findById(id).get();
		
		// (m2) null�̸� �ʰ� ������
		// Optional.orElseGet(Supplier<? extends User> supplier)
//		User user = userRepository.findById(id).orElseGet(supplier)
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
				// ������ �������̽� interface�� new���� -> �͸�Ŭ���� �ʿ���
				// DB�� ���� ������ �̰� �����
				
				// SupplierŸ��?
				// �͸�ü - Supplier�� �������̽�����(�������̽��� new �� ��ü �Ұ�)
//			@Override
//			public User get() {
//				return new User();  //��ü�� return(��ü�� user�� �־���)
//			}
//		});
		
		// (m3) ���� ���� �� (�߸��� �μ��� ������ throws)
		// CrudRepository
		// @throws IllegalArgumentException if {@literal id} is {@literal null}.
		// Supplier�� �������̽� => new(x) => new �͸�Ŭ���� 
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("�ش� ������ �����ϴ�. id : " + id);
			}
		});

		// ���ٽ� 
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("�ش����ڴ� ����. id : " + id);  // ; ������ ���� 
//		});
		
		// Optional<T> findById(ID id);
		// id�� �������� �ߴ� id��. 
		// null�� �� ���� ������ Optional�� User��ü ���μ� �����ð���
		// => ���� null���� �ƴ��� �Ǵ��ؾߵ�.
		
		System.out.println(user);
		
		//RestController -> Data ����
		// user ��ü = �ڹ� ������Ʈ
		// ��û : ��������
		
		// => (�ڹٰ�ü -> data ��ȯ�ؾߵǴµ�...) �ƹ�ư �׷���
		
		// ���������� user��ü�� ���ظ� ���� 
		//		=> user��ü�� ��ȯ���Ѿߵ�(json : ���������� ������ �� �ִ� ������)
		//		(Gson���̺귯�� (java��ü -> json�ϰ� ������))
		//		=> ��������Ʈ = MessageConverter��� �ְ� ����� �ڵ�����
		//		=> ���� �ڹٰ�ü�� �����ϸ�, MessageConver�� Jackson ���̺귯���� ȣ���ؼ�
		//		=> user��ü�� json���� ��ȯ���� ���������� �Ѱ���.
		return user;
	}
	
	// http://localhost:8077/blog/dummy/join
	// http�� body�� usernmae, password, email �����͸� ������ ��û
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
		
		return "ȸ�����ԿϷ�";
	}
}
