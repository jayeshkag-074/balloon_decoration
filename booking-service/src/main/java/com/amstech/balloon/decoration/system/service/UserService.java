package com.amstech.balloon.decoration.system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.balloon.decoration.system.entity.Location;
import com.amstech.balloon.decoration.system.entity.User;
import com.amstech.balloon.decoration.system.repo.LocationRepo;
import com.amstech.balloon.decoration.system.repo.UserRepo;
import com.amstech.balloon.decoration.system.request.Modal.UserLoginRequestModal;
import com.amstech.balloon.decoration.system.request.Modal.UserSignUpRequestModal;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private LocationRepo locationRepo;

	public void signup(UserSignUpRequestModal userSignUpRequestModal) throws Exception {
		Optional<Location> locationOptional = locationRepo.findById(userSignUpRequestModal.getLocationId());
		if (!locationOptional.isPresent()) {
			throw new Exception("location does not exist:" + userSignUpRequestModal.getLocationId());
		}
		User user = new User();
		user.setFirstName(userSignUpRequestModal.getFirstName());
		user.setLastName(userSignUpRequestModal.getLastName());
		user.setEmail(userSignUpRequestModal.getEmail());
		user.setMobileNumber(userSignUpRequestModal.getMobileNumber());
		user.setGender(userSignUpRequestModal.getGender());
		user.setPassword(userSignUpRequestModal.getPassword());
		user.setLocation(locationOptional.get());
		User saveUser = userRepo.save(user);
	}

	public void login(UserLoginRequestModal userLoginRequestModal) throws Exception {
		User byUserName = userRepo.findByUserName(userLoginRequestModal.getUserName(),
				userLoginRequestModal.getPassword());

		if (byUserName == null) {
			throw new Exception("user does not exist" + userLoginRequestModal.getUserName());
		}
	}

}
