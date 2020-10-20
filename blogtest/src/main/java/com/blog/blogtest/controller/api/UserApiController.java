package com.blog.blogtest.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogtest.dto.ResponseDto;
import com.blog.blogtest.model.RoleType;
import com.blog.blogtest.model.User;
import com.blog.blogtest.service.UserService;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService; //di
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user)
	{
		System.out.println("save!");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
