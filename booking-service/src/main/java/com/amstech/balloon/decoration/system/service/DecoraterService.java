package com.amstech.balloon.decoration.system.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.balloon.decoration.system.entity.Decorater;
import com.amstech.balloon.decoration.system.entity.Status;
import com.amstech.balloon.decoration.system.entity.User;
import com.amstech.balloon.decoration.system.modal.request.DecoraterUpdateStatusRequestModal;
import com.amstech.balloon.decoration.system.modal.request.DecoratorApplyRequestModal;
import com.amstech.balloon.decoration.system.repo.DecoraterRepo;
import com.amstech.balloon.decoration.system.repo.StatusRepo;
import com.amstech.balloon.decoration.system.repo.UserRepo;

@Service
public class DecoraterService {

	private final Logger LOGGER = LoggerFactory.getLogger(DecoraterService.class);

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private DecoraterRepo decoraterRepo;

	private int pendingId = 6;

	public DecoraterService() {
		LOGGER.debug("DecoraterService : Object Created");
	}

	public int apply(DecoratorApplyRequestModal decoratorApplyRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(decoratorApplyRequestModal.getUserId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is no Available with id  : " + decoratorApplyRequestModal.getUserId());
		}
		Optional<Status> statusOptional = statusRepo.findById(pendingId);
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + pendingId);
		}
		Decorater decorater = new Decorater();
		decorater.setUser(userOptional.get());
		decorater.setStatus(statusOptional.get());
		decorater.setExperience(decoratorApplyRequestModal.getExperience());
		decorater.setBio(decoratorApplyRequestModal.getBio());
		decorater.setCreatedAt(new Date());

		Decorater savedDecorater = decoraterRepo.save(decorater);
		return savedDecorater.getId();
	}

	public int updateStatus(DecoraterUpdateStatusRequestModal decoraterUpdateStatusRequestModal) throws Exception {
		Optional<Decorater> decoraterOptional = decoraterRepo.findById(decoraterUpdateStatusRequestModal.getId());
		if (!decoraterOptional.isPresent()) {
			throw new Exception("User Is no Available with id  : " + decoraterUpdateStatusRequestModal.getId());
		}
		Optional<Status> statusOptional = statusRepo.findById(decoraterUpdateStatusRequestModal.getStatusId());
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + decoraterUpdateStatusRequestModal.getStatusId());
		}

		if (decoraterOptional.get().getStatus().getId() == statusOptional.get().getId()) {
			throw new Exception("Booking Is Already in " + statusOptional.get().getName() + " Status.");
		}
		Decorater decorater= decoraterOptional.get();
		decorater.setStatus(statusOptional.get());

		Decorater saveDecorater = decoraterRepo.save(decorater);
		return saveDecorater.getStatus().getId();
	}

}
