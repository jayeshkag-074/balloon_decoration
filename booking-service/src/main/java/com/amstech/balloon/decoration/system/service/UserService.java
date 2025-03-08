package com.amstech.balloon.decoration.system.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amstech.balloon.decoration.system.modal.request.UserMobileNumberUpdate;
import com.amstech.balloon.decoration.system.modal.request.UserUpdateEmailModal;
import com.amstech.balloon.decoration.system.entity.Location;
import com.amstech.balloon.decoration.system.entity.User;
import com.amstech.balloon.decoration.system.repo.LocationRepo;
import com.amstech.balloon.decoration.system.repo.UserRepo;
import com.amstech.balloon.decoration.system.request.Modal.UserLoginRequestModal;
import com.amstech.balloon.decoration.system.request.Modal.UserSignUpRequestModal;


@Service
public class UserService {

	private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepo userRepo;
   @Autowired
	private UserRepo userRepo;

	@Autowired
	private LocationRepo locationRepo;

	public void updateEmail(UserUpdateEmailModal userUpdateEmailModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(userUpdateEmailModal.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + userUpdateEmailModal.getId());
		}
		User user = userOptional.get();
		user.setEmail(userUpdateEmailModal.getEmail());
		user.setUpdatedAt(new Date());
		userRepo.save(user);
	}

	public void UserMobileNumberUpdate(UserMobileNumberUpdate userMobileNumberUpdate) throws Exception {
		Optional<User> userOptional = userRepo.findById(userMobileNumberUpdate.getId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is Not Avilable with id :" + userMobileNumberUpdate.getId());
		}
		User user = userOptional.get();
		user.setMobileNumber(userMobileNumberUpdate.getMobileNumber());
		user.setUpdatedAt(new Date());
		userRepo.save(user);

	}
  
 

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
