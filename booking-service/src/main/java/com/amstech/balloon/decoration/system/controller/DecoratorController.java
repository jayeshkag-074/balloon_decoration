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

import com.amstech.balloon.decoration.system.modal.request.DecoraterUpdateStatusRequestModal;
import com.amstech.balloon.decoration.system.modal.request.DecoratorApplyRequestModal;
import com.amstech.balloon.decoration.system.service.DecoraterService;

@RestController
@RequestMapping("/decorator")
public class DecoratorController {

	private final Logger LOGGER = LoggerFactory.getLogger(DecoratorController.class);
	
	@Autowired
	private DecoraterService decoraterService;
	
	public DecoratorController() {
		LOGGER.info("DecoratorController : Object Created");
	}


	@RequestMapping(method = RequestMethod.POST, value ="/apply",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> create(@RequestBody DecoratorApplyRequestModal decoratorApplyRequestModal){
		
		LOGGER.info("Appling For Decorater with Userid: {} " ,decoratorApplyRequestModal.getUserId());
		try {
			int id = decoraterService.apply(decoratorApplyRequestModal);
			return new ResponseEntity<Object>("Successfully Apply For Decorater and decoarterId : " + id ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Appling For Decorater with Userid due to: {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Appling For Decorater with Userid due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@RequestMapping(method = RequestMethod.PUT, value ="/update-status",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateTripPlannerStatus(@RequestBody DecoraterUpdateStatusRequestModal decoraterUpdateStatusRequestModal ){
		
		LOGGER.info("Updating TripPlanner Status for id : {} " ,decoraterUpdateStatusRequestModal.getId());
		try {
			int statusId = decoraterService.updateStatus(decoraterUpdateStatusRequestModal);
			return new ResponseEntity<Object>("Decorater Status Update SuccessFully and Now Current Status : " + statusId,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Update TripPlanner Status due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Update Decorater Status due to : " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
