package com.semadec.semadec.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class SemadecController {
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
