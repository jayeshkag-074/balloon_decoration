package com.amstech.balloon.decoration.system.controller;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
	
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	public UserController() {
		LOGGER.info("User Controller : object Created ");
		
	}

}


