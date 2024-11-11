package com.connectly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connectly.config.JwtProvider;
import com.connectly.models.User;
import com.connectly.repository.UserRepository;
import com.connectly.request.LoginRequest;
import com.connectly.response.AuthResponse;
import com.connectly.service.CustomerUserDetailsService;
import com.connectly.service.UserService;

@RestController
@RequestMapping("/auth") 
public class AuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetails;
	
	@PostMapping("/signup")
	public AuthResponse createUser (@RequestBody User user) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		if(isExist!=null) {
			throw new Exception("email already used by another account");
		}
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
	
		newUser.setLastName(user.getLastName());
		newUser.setPassword( passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "Succesfully Registered");
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
	String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "Logged in Succesfully");
		return res;
	}

	private Authentication authenticate(String email, String password) {
	
		UserDetails userDetails = customerUserDetails.loadUserByUsername(email);
		if(userDetails==null) {
			throw new BadCredentialsException("invali username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("incorrect password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
