package com.jae.prj05.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jae.prj05.model.User;
import com.jae.prj05.repository.UserRepository;

// implements UserDetailsService 유저 정보 가져오는거

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
	// password 부분 처리는 알아서 .
	// username이 DB에 있는지만 확인해주면 됨.(이 함수해서 할거임)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
					.orElseThrow(()->{
						System.out.println("사용자x(prindipalDetailService");
						return new UsernameNotFoundException("해당사용자 찾을수 없음 : " + username);
					});
		System.out.println("PrincipalDetailService - principal : " + principal);
		// PrincipalDetailService - principal : User(id=1, username=user, password=$2a$11...zMJt9C, email=ejejej2002@gmail.com, role=USER, createDate=2023-02-25 11:41:38.777)
		
		return new PrincipalDetail(principal); 
		//시큐리티 세션에 유저정보가 저장됨. 그때 타입이
		// ( implements UserDetails) 
		// 아이디:user, 패스워드:콘솔창에 복붙한거
		
		//	return new PrincipalDetail();  이거는
		// 아이디:user, 패스워드:콘솔창에 복붙한거
	}
	
}
