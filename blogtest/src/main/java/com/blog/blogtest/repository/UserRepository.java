package com.blog.blogtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogtest.model.User;

//dao와 같음.
//자동으로 bean등록 됨.
public interface UserRepository extends JpaRepository<User,Integer>{//usertable의 프라이머리키는 integer
	
}
