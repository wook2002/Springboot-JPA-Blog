package com.jae.prj05.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jae.prj05.model.RoleType;
import com.jae.prj05.model.User;
import com.jae.prj05.repository.UserRepository;

// @Service 이런거 =>  컴포넌트스캔을 통해서 Bean등록 (IoC) (메모리에 대신 띄어줌)

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private BCryptPasswordEncoder encoder;
	
	// org.springframework.transaction.annotation.Transactional;
	@Transactional  // 트랜잭션됨 (성공하면 commit, 안되면 rollback)
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword(); 
		String encPassword = encoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		
		userRepository.save(user);  
		
	}

	@Transactional
	public void 회원수정(User user) {
		// 수정 => 영속성컨텍스트 User객체를 영속화 시키고
		// 영속화된 User객체를 수정.
		// Select로 영속화-> 영속화된 객체를 변경(자동 DB도 변경)
		
		// 영속화(영속성 컨텍스트에 DB정보 가져와 넣음)
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		
		// encode
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		
		// 영속성컨텍스트에 저장
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
		// 수정함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 자동됨
		// 영속화된 persistance객체의 변화가 감지되면 
		// 변화된 데이터를 맞춰줌(더티체킹)
		
		// session 정보도 바꿔줘야하는데(강제로 변경해줘야 함)
		// sequrity에선 까다로움
		
	}

//		try {
//			userRepository.save(user); // <S extends T> 상속관계 이루어진 클래스만 받음
//			return 1; // 정상
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("UserService : 회원가입() " + e.getMessage() );
//		}
//		return -1; // 비정상
	}

	/* 로그인(m1)
	// Select할 때 트랜잭션 시작, 서비스 종료시 트랜잭션 종료(정합성)
	@Transactional(readOnly = true) 
	public User 로그인(User user) {
//		System.out.println("UserService로그인1 : " + user);
		user = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		System.out.println("UserService로그인2 : " + user);
		return user;
	}
	

}*/
