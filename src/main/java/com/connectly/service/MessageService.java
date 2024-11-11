package com.connectly.service;

import java.util.List;

import com.connectly.models.Chat;
import com.connectly.models.Message;
import com.connectly.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
	
}
