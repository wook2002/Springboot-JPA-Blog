package com.jae.prj05.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jae.prj05.config.auth.PrincipalDetailService;

// 밑에 세개 어노테이션 걍 세트임 

@Configuration // 빈 등록(스프링컨테이너가 객체관리하게)
@EnableWebSecurity // Filter를 추가(시큐리티필더 등록)(스프링 시큐리티는 원래 활성 되어있는데 - 설정하겠음)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소(/auth등)로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // IoC가 됨(return new BCryptPasswordEncoder()를 스프링이 관리함)
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기 하는데
	// 해당 password가 뭘로 해시가 되어 회원가입되었는지 알아야
	// 같은 해시로 암호화해서 DB에 있는 해시랑 비교할 수 있음
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 패스워드 비교(DB에 해시로 된거랑)
		System.out.println("패스워드 비교 설정"); // (처음에 서버 실행할 때만 됨)
		auth
			.userDetailsService(principalDetailService)
			.passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰
			.authorizeHttpRequests() // 요청 들어오면(인가가 들어오면)
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
				.permitAll()
				.anyRequest() // 그외 모든것은
				.authenticated() // 인증되어있어야함
			.and()
				.formLogin()   // 인증 안된건 무조건 절로 감
				.loginPage("/auth/loginForm") // "/login" -> 로그인폼 바꿈
				.loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해줌
				.defaultSuccessUrl("/") // 로그인 성공하면 일로 감
//				.failureUrl("/fail") //로그인 실패하면 요기로
				;
		
		// loginForm :  <form action="/auth/loginProc" method="POST">
				
//			.antMatchers("/auth/loginForm","/auth/joinForm")
//			.csrf().disable();
	}
	
}