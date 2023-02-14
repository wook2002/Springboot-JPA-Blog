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
@Builder // ��������
@Entity // �̰� �ؿ� �ִ°� ����
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
	
// @ManyToOne => EAGER��� �⺻���� : Board�� select�ϸ�, ������ ����
//	@OneToMany = > LAZY��� �⺻���� : Board�� select�ϸ�, �ʿ��� ���� ������
//	@ManyToOne(fetch = FetchType.EAGER) // Many(Board) - One(User) => (n �� 1)
	@ManyToOne
	@JoinColumn(name="userId") // userId -> Board�� DB�÷���(FK)
	private User user; // (orm��)��ü�� ����ϸ� �ڵ� FK ������� 
// DB�� ������Ʈ�� ������ �� ����. FK, �ڹٴ� ������Ʈ ���尡��. => �浹
//	private int userId; // foreignKey => orm���� ������Ʈ�� �ؾߵ�)
//	FK : java�� ��ü, DB�� int 
	
	// mappedBy : reply�� �������������� �ƴϴ�(�� FK�� �ƴϴ�) 
	//						=> DB�� �÷��� ��������(Board���� FK��)
//	@JoinColumn(name="replyId") // FK �ʿ����(���ڼ�����)
//	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // (�Խñ�1:�亯n)
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // �ʼ��� �����ò��� ���� �ٲ�
	private List<Reply> reply;
	// �������� ���� = FK�� ���� ��ü(������Ʈ, object)
	// ORM Ư¡ 1. ���� User + Reply + Board �� join�ϰ� select�ϰ���
	//	�� ORM�� Board�� select�ϸ�� (JPA�� ����)
	// User 1�� 
	// Reply n�� => �÷���(java.util.List)
	
	@CreationTimestamp
	private Timestamp createDate; //java.sql
	
	
}
