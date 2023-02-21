package com.jae.prj05.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM��? Java��� �� Object -> ���̺�� �������ִ� ���.
// (Object Relational Mapping)

//@DynamicInsert  //insert�Ҷ� null�� �ʵ� ���� (�⺻�� ������ �⺻�� ���)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // ��������
@Entity //���̺�ȭ(User Ŭ������ Mysql�� ���̺��� ������.)
public class User {
	
	@Id //�⺻Ű(Primary key)(javax.persistence.Id;)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE) // (������), TABLE(���̺� ��ȣ ���), AUTO(�ڵ�), ���
	@GeneratedValue(strategy = GenerationType.IDENTITY) // (����� �ڵ��Է�)�ѹ�������(DB�ѹ��� ������ ����(������Ʈ�� �����)( ����Ŭ(������), mysql(auto_increment) )
	private int id; // 
	
	@Column(nullable = false, length = 30, unique = true) //( null�Ұ�, 30�ڰ� �ִ� )  
	private String username; // ���̵�
	
	@Column(nullable = false, length = 100) // �ؽ÷� �����ҰŶ� �˳��ϰ�(��ȣȭ)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	(m1) Role (@DynamicInsert�� �Ἥ default��)
//	@ColumnDefault("user") (x) =>  "  ' ���� '  "
//	@ColumnDefault("'user'")
//	private String role; //(ADMIN, USER, ...) Stirng���� Enum�� ���°� ����(�����μ�������(��������: adminnn2 �̷��� ���ϰ�))

//	(m2) Role (Enum�� ���°� ���� -> ���⿡ �ִ� Ÿ�Ը� ��������)
//	DB�� RoleType�̶�°� ���� => �� EnumŸ���� String�̶�� �˷���ߵ�
	@Enumerated(EnumType.STRING)
	private RoleType role;
//	import javax.persistence.EnumType;
//	import javax.persistence.Enumerated;
	
	
	@CreationTimestamp // (����� �ڵ��Է�)�ð��ڵ��Է� <= DB�� �Ⱦ���(oracle(sysdate), mysql(now)) 
	private Timestamp createDate; //java.sql
}