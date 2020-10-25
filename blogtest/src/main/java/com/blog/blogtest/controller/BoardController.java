package com.blog.blogtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.blog.blogtest.config.auth.PrincipalDetail;
import com.blog.blogtest.service.BoardService;

@Controller
public class BoardController{
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)Pageable pageable) //컨트롤러에서 스프링 시큐리티 세션에서  찾는방법
	{
		model.addAttribute("boards",boardService.글목록(pageable));//model은 리퀘스트정보
		return "index"; //그냥 컨트롤러라 viewResolver가 처리 리졸버가 model의 정보를 글목록을 장착한다음 board란 이름으로 jsp로 전송
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.글자세히보기(id));
		return "board/detail";
	}
	
	//user 권한으로.
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id,Model model) {
		model.addAttribute("board",boardService.글자세히보기(id));
		return "board/updateForm";
	}
	
	
}
