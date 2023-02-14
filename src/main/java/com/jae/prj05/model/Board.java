package com.jae.prj05.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴
@Entity // 이건 밑에 있는게 좋음
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
	
// @ManyToOne => EAGER라는 기본전략 : Board를 select하면, 무조건 들고와
//	@OneToMany = > LAZY라는 기본전략 : Board를 select하면, 필요할 때만 들고오기
//	@ManyToOne(fetch = FetchType.EAGER) // Many(Board) - One(User) => (n 대 1)
	@ManyToOne
	@JoinColumn(name="userId") // userId -> Board의 DB컬럼명(FK)
	private User user; // (orm은)객체를 사용하면 자동 FK 만들어짐 
// DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트 저장가능. => 충돌
//	private int userId; // foreignKey => ormㄴㄴ 오브젝트로 해야됨)
//	FK : java는 객체, DB는 int 
	
	// mappedBy : reply는 연관관계주인이 아니다(난 FK가 아니다) 
	//						=> DB에 컬럼을 만들지마(Board쪽이 FK임)
//	@JoinColumn(name="replyId") // FK 필요없음(원자성깨짐)
//	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // (게시글1:답변n)
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 필수로 가져올꺼라서 전략 바꿈
	private List<Reply> reply;
	// 연관관계 주인 = FK를 가진 객체(오브젝트, object)
	// ORM 특징 1. 보통 User + Reply + Board 다 join하고 select하곤함
	//	⇒ ORM은 Board만 select하면됨 (JPA가 해줌)
	// User 1개 
	// Reply n개 => 컬렉션(java.util.List)
	
	@CreationTimestamp
	private Timestamp createDate; //java.sql
	
	
}
