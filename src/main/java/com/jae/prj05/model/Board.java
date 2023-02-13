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
	
	@Lob // ��뷮������
	private String content; // (��)���ӳ�Ʈ ���̺귯�� <html>�±װ� ������ �����ε�(�뷮ŭ)
	
	@ColumnDefault("0") //���� vs @ColumnDefault("'user'") ����
	private int count; // ��ȸ��
	
	@ManyToOne // Many(Board) - One(User) => (n �� 1)
	@JoinColumn(name="userId") // userId -> Board�� DB�÷���(FK)
	private User user; // (orm��)��ü�� ����ϸ� �ڵ� FK ������� 
// DB�� ������Ʈ�� ������ �� ����. FK, �ڹٴ� ������Ʈ ���尡��. => �浹
//	private int userId; // foreignKey => orm���� ������Ʈ�� �ؾߵ�)
	
	@CreationTimestamp
	private Timestamp createDate; //java.sql
	
	
}
