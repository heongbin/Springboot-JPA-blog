package com.blog.blogtest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //원본
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 jpa영속성 컨텍스트 user오브젝트 영속화, 영속화된 오브젝트 수정.
		//select를 통해 user오브젝트를 db로부터 가져오면 영속화됨.
		//영속화후 업데이트하면 db에 자동으로 커밋.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("fail");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//함수 종료 :트랜잭션 끝-> commit
		
	}
	
	/*@Transactional(readOnly = true) //select 할 때 트랜잭션ㄴ 시작.,서비스 종료시에 트랜잭션 종료(정합성때문)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		
	}*/
	
	
}
