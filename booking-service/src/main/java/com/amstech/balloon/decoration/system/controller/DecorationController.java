package com.amstech.balloon.decoration.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amstech.balloon.decoration.system.modal.response.DecorationResponseModal;
import com.amstech.balloon.decoration.system.service.DecorationService;

@RestController
@RequestMapping("decoration")
public class DecorationController {

	private final Logger LOGGER = LoggerFactory.getLogger(DecorationController.class);
	
	@Autowired
	private DecorationService decorationService;
	
	public DecorationController() {
		LOGGER.info("DecorationController : Object Created");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
	public ResponseEntity<Object> findAll() {

		LOGGER.info("Fetching All Decoration Available");
		try {
			List<DecorationResponseModal> decorationResponseModals = decorationService.findAllContinue();
			return new ResponseEntity<Object>(decorationResponseModals, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to Fetching All Decoration Availables due to : {}", e.getMessage(), e);
			return new ResponseEntity<Object>("Failed to Fetching All Decorations Availables due to : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
