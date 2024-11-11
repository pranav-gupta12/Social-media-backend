package com.connectly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectly.models.Reels;
import com.connectly.models.User;
import com.connectly.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService{

	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;
	@Override
	public Reels createReel(Reels reel, User user) {
		Reels createReel = new Reels();
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		userService.findUserbyId(userId);
	return reelsRepository.findByUserId(userId);
	}

	@Override
	public List<Reels> findAllReeels() {
		
		return reelsRepository.findAll();
	}

}
