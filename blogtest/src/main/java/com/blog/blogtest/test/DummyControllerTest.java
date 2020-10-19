package com.blog.blogtest.test;


import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogtest.model.Reply;
import com.blog.blogtest.model.RoleType;
import com.blog.blogtest.model.User;
import com.blog.blogtest.repository.UserRepository;

@RestController
public class DummyControllerTest {
	@Autowired //스프링이 컴포넌트 스캔을 할떄, userRepository를 di해줌. 
	private UserRepository userRepository;
	//post라 request http의 body에 username,password,email 데이터를 가지고 요청함.
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			return "delete fail";
		}
		return "complete"+id;
	}
	 
	
	@Transactional//set구문으로 change 되기때문에 이걸감지한 트랜잭션으로 이기능을 마무리.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {//requestBody는 json을 받을떼
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("fail");
		});//jpa의 캐쉬에서 영속화
		
		user.setEmail("abc@naver.com");
		user.setPassword("123");
	
		userRepository.save(requestUser);
 		return user;
	}
	
	@GetMapping("/dummy/user")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	
	//페이지
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> Paginguser = userRepository.findAll(pageable);
		List<User> users = Paginguser.getContent();
		return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("can't user:"+ id);
			}
		});//optinal은 null방지용
		return user;//스프링 부트가 자바오브젝트를 리턴하면 내부에서 messageconverter가 자동으로 jackson라ㅣ브러리를 통해서 json으로 변환해줌
	}
	
	@PostMapping("dummy/join")
	public String join(User user)
	{
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 성공";
		
	}
}
