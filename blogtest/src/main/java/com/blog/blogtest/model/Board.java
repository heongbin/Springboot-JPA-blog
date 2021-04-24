package com.blog.blogtest.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;



@Entity//클래스가 mysql테이블이 자동생성.
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> reply) {
		this.replys = reply;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	@Column(columnDefinition = "integer default 0")
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) //many = board -> user = one
	@JoinColumn(name="userId")//db에서는 forignkey
	private User user; 
	
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)//mappedBy는 주인관계 표시. board가 주인이기때문에 reply가 board를 포린키로 갖음. reply클래스가 갖고잇는 아이디명.
	@JsonIgnoreProperties({"board"})
	@javax.persistence.OrderBy("id desc")
	private List<Reply> replys;//커럼에 추가는X. 1정규화떄문에
	
	@CreationTimestamp
	private Timestamp createDate;

	@Lob
	private String content;
}
