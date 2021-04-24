package com.blog.blogtest.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogtest.model.Board;
import com.blog.blogtest.repository.BoardRepository;

@RestController
public class ReplyControllerTest {
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRepository.findById(id).get();
	}
}
