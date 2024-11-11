package com.connectly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.connectly.models.Message;
import com.connectly.models.User;
import com.connectly.repository.MessageRepository;
import com.connectly.service.MessageService;
import com.connectly.service.UserService;

@RestController
public class CreateMessage {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@PathVariable Integer chatId,
			@RequestBody Message req,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Message message = messageService.createMessage(user, chatId, req);
		return message;
		
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatsMessages(@PathVariable Integer chatId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		List<Message> message = messageService.findChatsMessages(chatId);
		return message;
		
	}
}

