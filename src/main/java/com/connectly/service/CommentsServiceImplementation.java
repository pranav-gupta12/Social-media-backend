package com.connectly.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectly.models.Comment;
import com.connectly.models.Post;
import com.connectly.models.User;
import com.connectly.repository.CommentRepository;
import com.connectly.repository.PostRepository;

@Service
public class CommentsServiceImplementation implements CommentService{

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	@Override
	public Comment createComments(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User user = userService.findUserbyId(userId);
		
		Post post = postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		Comment savedComment = commentRepository.save(comment);
		post.getComments().add(savedComment);
		postRepository.save(post);
		
		
		return savedComment;
			
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		Optional<Comment> opt = commentRepository.findById(commentId);
		if(opt.isEmpty()) {
			throw new Exception("No such comment exist");
		}
		return opt.get();
	}

	@Override
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Comment comment = findCommentById(CommentId);
		User user = userService.findUserbyId(userId);
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}
		else {
			comment.getLiked().remove(user);
		}
		return commentRepository.save(comment);
	}

}
