package com.MediLabo.MSFrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {


	@GetMapping("/")
	public String viewSlachPage() {
		return "login";
	}
	
	@GetMapping("/login")
	public String viewLogin() {
		return "login.html";
	}

	@GetMapping("/home")
	public String viewHomePage() {
		return "home.html";
	}

	
}
