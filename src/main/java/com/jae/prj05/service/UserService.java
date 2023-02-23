package com.jae.prj05.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jae.prj05.model.User;
import com.jae.prj05.repository.UserRepository;

// @Service 이런거 =>  컴포넌트스캔을 통해서 Bean등록 (IoC) (메모리에 대신 띄어줌)

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional  // 트랜잭션됨 (성공하면 commit, 안되면 rollback)
	public void 회원가입(User user) {
		userRepository.save(user);  
		
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

}
