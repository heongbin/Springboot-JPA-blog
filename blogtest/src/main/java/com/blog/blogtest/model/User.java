package com.blog.blogtest.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity//클래스가 mysql테이블이 자동생성.
public class User {
	@Id//primary key
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //auto_increment 
	
	@Column(nullable = false,length = 30)
	private String username;//아이디
	
	@Column(nullable = false,length = 100)//->hash로
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; //enum으로 change // admin,user,manager
	
	
	@CreationTimestamp//time auto 
	private Timestamp createDate;
	
}
