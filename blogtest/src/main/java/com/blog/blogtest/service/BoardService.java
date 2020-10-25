package com.blog.blogtest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.blogtest.model.Board;
import com.blog.blogtest.model.RoleType;
import com.blog.blogtest.model.User;
import com.blog.blogtest.repository.BoardRepository;
import com.blog.blogtest.repository.UserRepository;

@Service//스프링이 컴포넌트 스캔을 통해 bean에 등록. ioc 스프링이 인스턴스생성해서 가지고있음.
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
		
	}
	
	@Transactional
	public void 글삭(int id)
	{
		boardRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	
	}
	
	@Transactional(readOnly = true)
	public Board 글자세히보기(int id)
	{
		return boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글자세히보기 fail");
		});
	}
	/*@Transactional(rdeadOnly = true) //select 할 때 트랜잭션ㄴ 시작.,서비스 종료시에 트랜잭션 종료(정합성때문)
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		
	}*/
	@Transactional
	public void 글수정하기(int id, Board requestboard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 fail");});
				board.setTitle(requestboard.getTitle());
				board.setContent(requestboard.getContent());
				
		
	}
	
	
}
