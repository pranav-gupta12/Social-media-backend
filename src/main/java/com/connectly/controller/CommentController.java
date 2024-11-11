package com.connectly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.connectly.models.Comment;
import com.connectly.models.Post;
import com.connectly.models.User;
import com.connectly.service.CommentService;
import com.connectly.service.PostService;
import com.connectly.service.UserService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment (@RequestBody Comment comment, @RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comment createdComment =  commentService.createComments(comment, postId, user.getId());
		
		return createdComment;
		
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment createComment ( @RequestHeader("Authorization") String jwt, @PathVariable Integer commentId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comment likedComment =  commentService.likeComment(commentId, user.getId());
		
		return likedComment;
		
	}
	
	
}
