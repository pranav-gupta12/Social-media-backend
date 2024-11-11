package com.connectly.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

	@GetMapping
	public String homeControllerHandler() {
		return "This is Home Controller";
	}
	
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "This is Home Controller2 with mapping";
	}
	@GetMapping("/codewithpranav")
	public String homeControllerHandler3() {
		return "Hello Pranav siuuuuuu";
	}
}
