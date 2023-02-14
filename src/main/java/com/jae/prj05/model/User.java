package com.jae.prj05.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM��? Java��� �� Object -> ���̺�� �������ִ� ���.
// (Object Relational Mapping)

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
	
	@Column(nullable = false, length = 30) //( null�Ұ�, 30�ڰ� �ִ� )  
	private String username; // ���̵�
	
	@Column(nullable = false, length = 100) // �ؽ÷� �����ҰŶ� �˳��ϰ�(��ȣȭ)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email2;
	
//	@ColumnDefault("user") (x) =>  "  ' ���� '  "
	@ColumnDefault("'user'")
	private String role; //(admin, user, manager...) Stirng���� Enum�� ���°� ����(�����μ�������(��������: adminnn2 �̷��� ���ϰ�)) 
	
	@CreationTimestamp // (����� �ڵ��Է�)�ð��ڵ��Է� <= DB�� �Ⱦ���(oracle(sysdate), mysql(now)) 
	private Timestamp createDate; //java.sql
}