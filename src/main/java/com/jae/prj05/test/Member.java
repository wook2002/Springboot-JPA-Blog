package com.jae.prj05.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@RequiredArgsConstructor // final 붙은 애들의 생성자(final 없는 애들은 안만들어짐)
@Data  // @Getter + @Setter
@NoArgsConstructor
//@AllArgsConstructor => @Builder
public class Member {
//	어차피 DB에서 가져오니까 데이터변하지 않게 final(불변성)
//	private final int id;
	private int id;
	private String username; 
	private String password;
	private String email;
	
//	@Builder 
//	장점 : 순서필요없음, 매개변수 있는 생성자도 이거 하나면 끝
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
//	public Member() {
//	}
//
//	public Member(int id, String username, String password, String email) {
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.email = email;
//	}
//
//	@Override
//	public String toString() {
//		return "Member [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

}