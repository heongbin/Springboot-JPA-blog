package com.blog.blogtest.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.blogtest.config.auth.PrincipalDetail;

@Controller
public class BoardController{
	
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) //컨트롤러에서 스프링 시큐리티 세션에서  찾는방법
	{
		System.out.println("로그인 사용자 아이디:" + principal.getUsername());
		return "index";
	}
}
