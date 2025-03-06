package com.amstech.balloon.decoration.system.service;



import java.util.List;

import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.balloon.decoration.system.converter.modal.DecorationEntityToModalConverter;
import com.amstech.balloon.decoration.system.entity.Decoration;
import com.amstech.balloon.decoration.system.modal.response.DecorationResponseModal;
import com.amstech.balloon.decoration.system.repo.DecorationRepo;
import com.amstech.balloon.decoration.system.repo.StatusRepo;
@Service
public class DecorationService {

	private final Logger LOGGER = LoggerFactory.getLogger(DecorationService.class);

	@Autowired
	private DecorationRepo decorationRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private DecorationEntityToModalConverter decorationEntityToModalConverter;

	private Integer continueStatusId = 11;
	private Integer disContinueStatusId = 12;

	
	

	public DecorationService() {
		LOGGER.debug("DecorationService : Object created");
	}




	public List<DecorationResponseModal> findAllContinue() throws Exception {

		List<Decoration> decorations = decorationRepo.findAllByContinueStatusId(continueStatusId);
		if (decorations.isEmpty()) {
			throw new Exception("No Decorations Available");
		}
		List<DecorationResponseModal> decorationResponseModals = decorationEntityToModalConverter.findAll(decorations);
		return decorationResponseModals;

	}
}
