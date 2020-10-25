package com.blog.blogtest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.blogtest.model.Board;
import com.blog.blogtest.model.User;

//dao와 같음.
//자동으로 bean등록 됨.
public interface BoardRepository extends JpaRepository<Board,Integer>{//usertable의 프라이머리키는 integer
	
}
