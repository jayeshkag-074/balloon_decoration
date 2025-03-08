package com.amstech.balloon.decoration.system.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.balloon.decoration.system.converter.modal.StateEntityToModalConverter;
import com.amstech.balloon.decoration.system.entity.State;
import com.amstech.balloon.decoration.system.modal.response.StateResponseModal;
import com.amstech.balloon.decoration.system.repo.StateRepo;

@Service
public class StateService {

private final Logger LOGGER = LoggerFactory.getLogger(StateService.class);
	
	@Autowired
	private StateRepo stateRepo;
	@Autowired
	private StateEntityToModalConverter stateEntityToModalConverter;
	
	public StateService() {
		LOGGER.debug("StateService : Object Created");
	}
	public List<StateResponseModal> findAll() throws Exception{
		List<State> states = stateRepo.findAll();
		if(states.isEmpty()) {
			throw new Exception("States is Not Avilable in Database");
		}
		
		List<StateResponseModal> stateResponseModals = stateEntityToModalConverter.findAll(states);
		return stateResponseModals;
	}

}
