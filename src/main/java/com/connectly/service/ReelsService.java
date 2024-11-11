package com.connectly.service;

import java.util.List;

import com.connectly.models.Reels;
import com.connectly.models.User;

public interface ReelsService {

	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findUsersReel(Integer userId) throws Exception;
	
	public List<Reels> findAllReeels ();
	
}
