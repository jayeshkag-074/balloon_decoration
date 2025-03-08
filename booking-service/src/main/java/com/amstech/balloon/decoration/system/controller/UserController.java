package com.amstech.balloon.decoration.system.controller;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.balloon.decoration.system.modal.request.UserMobileNumberUpdate;
import com.amstech.balloon.decoration.system.modal.request.UserUpdateEmailModal;
import com.amstech.balloon.decoration.system.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	
	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	 @Autowired
	 private UserService userservice;
	
	public UserController() {
		LOGGER.info("User Controller : object Created ");
		
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update-email", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateEmail(@RequestBody UserUpdateEmailModal userUpdateEmailModal ) {
		LOGGER.info("Updating user detail with id: {} ", userUpdateEmailModal.getId());
		try {
			userservice.updateEmail(userUpdateEmailModal);
			return new ResponseEntity<>("Update success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Update Failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/update-phoneNumber", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> UpdateMobileNumber(@RequestBody UserMobileNumberUpdate userMobileNumberUpdate ) {
		LOGGER.info("Updating user detail with id: {} ", userMobileNumberUpdate.getId());
		try {
			userservice.UserMobileNumberUpdate(userMobileNumberUpdate);
			return new ResponseEntity<>("Update success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Update Failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}


