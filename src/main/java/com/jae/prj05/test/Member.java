package com.jae.prj05.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@RequiredArgsConstructor // final ���� �ֵ��� ������(final ���� �ֵ��� �ȸ������)
@Data  // @Getter + @Setter
@NoArgsConstructor
//@AllArgsConstructor => @Builder
public class Member {
//	������ DB���� �������ϱ� �����ͺ����� �ʰ� final(�Һ���)
//	private final int id;
	private int id;
	private String username; 
	private String password;
	private String email;
	
//	@Builder 
//	���� : �����ʿ����, �Ű����� �ִ� �����ڵ� �̰� �ϳ��� ��
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
