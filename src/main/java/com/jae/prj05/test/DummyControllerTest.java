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

// RestController : data����
// Controller : html����
@RestController
public class DummyControllerTest {
	
//	������Ʈ ��ĵ�� ��, UserRepository�� �޸𸮿� ���ִ��� => ����ϱ⸸�ϸ� ��
//	DummyControllerTest�� �޸𸮿� �� ��, DI 
	@Autowired // ����������(DI)
	private UserRepository userRepository;
	
	
//	{id} url�ּ� => �Ķ����
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
		
		// (m3) ���� ���� �� (�߸��� �μ��� ������ throws), ���׸� �ӿ���
		// CrudRepository
		// @throws IllegalArgumentException if {@literal id} is {@literal null}.
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
		// ��û : ��������
		// user ��ü = �ڹ� ������Ʈ
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
