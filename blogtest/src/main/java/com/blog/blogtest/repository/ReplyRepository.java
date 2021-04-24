package com.blog.blogtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogtest.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
