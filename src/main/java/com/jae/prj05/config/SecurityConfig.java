package com.jae.prj05.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 밑에 세개 어노테이션 걍 세트임 

@Configuration // 빈 등록(스프링컨테이너가 객체관리하게)
@EnableWebSecurity // Filter를 추가(시큐리티필더 등록)(스프링 시큐리티는 원래 활성 되어있는데 - 설정하겠음)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소(/auth등)로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests() // 요청 들어오면(인가가 들어오면)
				.antMatchers("/auth/**")
				.permitAll()
				.anyRequest() // 그외 모든것은
				.authenticated() // 인증되어있어야함
			.and()
				.formLogin()   
				.loginPage("/auth/loginForm"); // "/login" -> 로그인폼 바꿈
			
//			.antMatchers("/auth/loginForm","/auth/joinForm")
//			.csrf().disable();
	}
	
}