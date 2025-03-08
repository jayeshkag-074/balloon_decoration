package com.amstech.balloon.decoration.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.balloon.decoration.system.modal.request.UserRoleAssigneRequestModal;
import com.amstech.balloon.decoration.system.modal.response.UserRoleResponseModal;
import com.amstech.balloon.decoration.system.service.UserRoleService;

@RestController
@RequestMapping("/user-role")
public class UserRoleController {

private final Logger LOGGER = LoggerFactory.getLogger(UserRoleController.class);
	
	@Autowired
	private UserRoleService userRoleService;

	public UserRoleController() {
		LOGGER.info("UserRoleConroller : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="/assigne",consumes = "application/json")
	public ResponseEntity<Object> assigneRole(@RequestBody UserRoleAssigneRequestModal userRoleAssigneRequestModal){
		
		LOGGER.info("Start Assinging Role to User whose id  : {} " ,userRoleAssigneRequestModal.getUserId());
		try {
			int id = userRoleService.assigneRole(userRoleAssigneRequestModal);
			return new ResponseEntity<Object>("Successfully Assigne Role to user and userRole id is : " + id ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Assigne Role to user due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Assigne Role to user due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/by-role", produces = "application/json")
	public ResponseEntity<Object> findByRoleId(@RequestParam("roleId") Integer roleId){
		
		LOGGER.info("Start Fetching User with roleId : {} " ,roleId);
		try {
			List<UserRoleResponseModal> userRoleResponseModals = userRoleService.findByRoleId(roleId);
			return new ResponseEntity<Object>(userRoleResponseModals,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching User by Role due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching User by Role  due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/by-user", produces = "application/json")
	public ResponseEntity<Object> findByUserId(@RequestParam("userId") Integer userId){
		
		LOGGER.info("Start Fetching Role of UserId : {} " ,userId);
		try {
			List<UserRoleResponseModal> userRoleResponseModals = userRoleService.findByUserId(userId);
			return new ResponseEntity<Object>(userRoleResponseModals,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching Roles of user due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching Roles of user  due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
