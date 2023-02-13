package com.jae.prj05.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

// ORM란? Java등등 그 Object -> 테이블로 매핑해주는 기술.

@Entity //테이블화(User 클래스가 Mysql에 테이블이 생성됨.)
public class User {
	
	@Id //기본키(Primary key)(javax.persistence.Id;)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE) // (시퀀스), TABLE(테이블에 번호 등등), AUTO(자동), 등등
	@GeneratedValue(strategy = GenerationType.IDENTITY) // (비워도 자동입력)넘버링전략(DB넘버링 전략을 따라감(프로젝트에 연결된)( 오라클(시퀀스), mysql(auto_increment) )
	private int id; // 
	
	@Column(nullable = false, length = 30) //( null불가, 30자가 최대 )  
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 해시로 변경할거라서 넉넉하게(암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email2;
	
//	@ColumnDefault("user") (x) =>  "  ' 문자 '  "
	@ColumnDefault("'user'")
	private String role; //(admin, user, manager...) Stirng말고 Enum을 쓰는게 좋다(도메인설정가능(범위가능: adminnn2 이런거 안하게)) 
	
	@CreationTimestamp // (비워도 자동입력)시간자동입력 <= DB꺼 안쓰고(oracle(sysdate), mysql(now)) 
	private Timestamp createDate; //java.sql
}