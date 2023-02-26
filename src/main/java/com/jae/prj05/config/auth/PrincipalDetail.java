package com.jae.prj05.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jae.prj05.model.User;

import lombok.Getter;

// UserDetails : 사용자의 정보를 (스프링시큐리티)세션에 담는거

// 스프링시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 
// UserDetails 타입의 객체를 
// 스프링 시큐리티의 고유한 세션저장소에 저장함.
// (UserDetails 타입의 PrincipalDetail이게 저장됨)
@Getter
public class PrincipalDetail implements UserDetails{
	
	// 그래서 PrincipalDetail가 저장될 때 얘가 포함 되어 있어야함
	private User user;  // 콤포지션(객체를 품고 있는것) vs extends User(상속)

	// 생성자
	public PrincipalDetail(User user) {
		//System.out.println("===testUserDetails===");
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		//System.out.println("===testUserDetails===");
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		//System.out.println("===testUserDetails===");
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다 (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		//System.out.println("===testUserDetails===");
		return true;
	}

	// 계정이 잠겨있는지 리턴함(true : 안잠겨있음)
	@Override
	public boolean isAccountNonLocked() {
		//System.out.println("===testUserDetails===");
		return true;
	}

	// 비밀번호가 만료되었는지(true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		//System.out.println("===testUserDetails===");
		return true;
	}

	// 계정 활성화(사용가능)인지 (true: 활성화)
	@Override
	public boolean isEnabled() {
		//System.out.println("===testUserDetails===");
		return true;
	}
	
	// ---- 계정이 갖고있는 권한 목록을 리턴한다.
	// (권한이 여러개 있을 수 있으니 루프를 돌아야하는데 우리는 한개만)
	
	// 컬렉션인데 이상한거 상속함(GrantedAuthority)
	// ArrayList <<< Collection
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		
		// (1) 자바는 method(메서드) 불가능 => method(객체)는 가능
		// (2) 오버라이딩 된 메서드가 하나 밖에 없음 (람다식으로 한다면?)
		// (3) 하나밖에 없으니 ()->{} 이렇게 다 줄이는거 가능.
		
//		collectors.add(new GrantedAuthority(){
//				@Override
//				public String getAuthority() {
//					return "ROLE_"+user.getRole();  // ROLE_USER (ROLE_은 그냥 규칙)
//				}
//		});
		
		System.out.println("UserDetails-getAuthorities() " );
		// 로그인 성공할 때만  됨
		
		// (스프링시큐리티)세션에 담을 때, 권한의 규칙은 ROLE_
		collectors.add(()->{ return "ROLE_" + user.getRole(); } );
		
		return collectors;
	}
}
