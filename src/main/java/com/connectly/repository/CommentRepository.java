package com.connectly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectly.models.Comment;

public interface CommentRepository  extends JpaRepository<Comment, Integer>{

}
