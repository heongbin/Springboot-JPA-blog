package com.blog.blogtest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.blogtest.model.RoleType;
import com.blog.blogtest.model.User;
import com.blog.blogtest.repository.UserRepository;

@Service//스프링이 컴포넌트 스캔을 통해 bean에 등록. ioc 스프링이 인스턴스생성해서 가지고있음.
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //원본
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
	}
	
	/*@Transactional(readOnly = true) //select 할 때 트랜잭션ㄴ 시작.,서비스 종료시에 트랜잭션 종료(정합성때문)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		
	}*/
	
	
}
