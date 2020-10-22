package com.blog.blogtest.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blogtest.model.User;
import com.blog.blogtest.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)//자바 스프링 시큐리티에게 아이디가 있는지 확인하는작업.
				.orElseThrow(()->{
					return new UsernameNotFoundException("user x:"+username);
				});
		return new PrincipalDetail(principal); //시큐리티 세션에 유저정보가 저장됨.
	}
}
