package com.blog.blogtest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogtest.model.User;
import com.blog.blogtest.repository.UserRepository;

@Service//스프링이 컴포넌트 스캔을 통해 bean에 등록. ioc 스프링이 인스턴스생성해서 가지고있음.
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
		
	}
}
