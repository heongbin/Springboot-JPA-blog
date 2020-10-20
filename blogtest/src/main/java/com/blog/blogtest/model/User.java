package com.blog.blogtest.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity//클래스가 mysql테이블이 자동생성.
//@DynamicInsert//insert 할때 null값 -> default값 때문에
public class User {
	@Id//primary key
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id; //auto_increment 
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public User()
	{
		
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public RoleType getRole() {
		return role;
	}


	public void setRole(RoleType role) {
		this.role = role;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Column(nullable = false,length = 30 ,unique = true)
	private String username;//아이디
	
	@Column(nullable = false,length = 100)//->hash로
	private String password;
	
	@Column(nullable = false,length = 50)
	private String email;
	
	//db는 roletype은 x
	@Enumerated(EnumType.STRING)
	private RoleType role; //enum으로 change // admin,user,manager
	
	
	@CreationTimestamp//time auto 
	private Timestamp createDate;
	
}
