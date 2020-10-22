package com.blog.blogtest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.blogtest.model.User;

//dao와 같음.
//자동으로 bean등록 됨.
public interface UserRepository extends JpaRepository<User,Integer>{//usertable의 프라이머리키는 integer
	//jpa의 네이밍으로 쿼리문실행가능
	//select * from user where username = 1파리미터 and password = 2파라미터;
	//User findByUsernameAndPassword(String username, String password);
	
	//@Query(value="select * from user where username = ?1 and password = ?2",nativeQuery = true)
	//User login(String username, String password);
	Optional<User> findByUsername(String username);
	
}
