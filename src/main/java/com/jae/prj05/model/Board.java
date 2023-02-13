package com.jae.prj05.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량데이터
	private String content; // (툴)섬머노트 라이브러리 <html>태그가 섞여서 디자인됨(용량큼)
	
	@ColumnDefault("0") //숫자 vs @ColumnDefault("'user'") 문자
	private int count; // 조회수
	
	@ManyToOne // Many(Board) - One(User) => (n 대 1)
	@JoinColumn(name="userId") // userId -> Board의 DB컬럼명(FK)
	private User user; // (orm은)객체를 사용하면 자동 FK 만들어짐 
// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트 저장가능. => 충돌
//	private int userId; // foreignKey => ormㄴㄴ 오브젝트로 해야됨)
	
	@CreationTimestamp
	private Timestamp createDate; //java.sql
	
	
}
