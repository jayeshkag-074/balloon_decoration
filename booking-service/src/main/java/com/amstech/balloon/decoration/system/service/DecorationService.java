package com.amstech.balloon.decoration.system.service;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.balloon.decoration.system.converter.modal.DecorationEntityToModalConverter;
import com.amstech.balloon.decoration.system.entity.Decoration;
import com.amstech.balloon.decoration.system.entity.Status;
import com.amstech.balloon.decoration.system.modal.response.DecorationDetailResponseModal;
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
	public DecorationDetailResponseModal findById(Integer id) throws Exception {
		Optional<Decoration> decorationOptional = decorationRepo.findById(id);
		if (!decorationOptional.isPresent()) {
			throw new Exception("No Decoration Available with id : " + id);
		}
		Decoration decoration = decorationOptional.get();
		if (decoration.getStatus().getId() != continueStatusId) {
			throw new Exception("That Decoration is Not Continue in Current Time where Decoration id : " + id);
		}

		DecorationDetailResponseModal decorationDetailResponseModal = decorationEntityToModalConverter.findById(decoration);
		return decorationDetailResponseModal;

	}
	
	public String ContinueDiscontinueDecorationStatus(Integer decorationId) throws Exception {
		Optional<Decoration> decorationOptional = decorationRepo.findById(decorationId);
		
		if(!decorationOptional.isPresent()) {
			throw new Exception("That Decoration is Not Available with id " + decorationId);
		}
		Decoration decoration = decorationOptional.get();
			Integer currentStatusId = decoration.getStatus().getId();

			// ContinueDiscontinue status: If 1 (continue), change to 0 (discontinue), else change to 1
			// (continue)
			Integer newStatusId = (currentStatusId == 11) ? 12 : 11;

			// Fetch the new status from the Status table
			 Optional<Status> statusOptional = statusRepo.findById(newStatusId);
			if (!statusOptional.isPresent()) {
				throw new Exception("That DecorationStaus is Not Available in Your Database with id " + newStatusId);
			} 
			decoration.setStatus(statusOptional.get());
			
			Decoration updateDecoration = decorationRepo.save(decoration);
			return updateDecoration.getStatus().getName();
		} 
	}

