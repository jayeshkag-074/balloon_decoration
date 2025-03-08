package com.amstech.balloon.decoration.system.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.balloon.decoration.system.converter.modal.UserRoleEntityToModalConverter;
import com.amstech.balloon.decoration.system.entity.Role;
import com.amstech.balloon.decoration.system.entity.User;
import com.amstech.balloon.decoration.system.entity.UserRole;
import com.amstech.balloon.decoration.system.modal.request.UserRoleAssigneRequestModal;
import com.amstech.balloon.decoration.system.modal.response.UserRoleResponseModal;
import com.amstech.balloon.decoration.system.repo.RoleRepo;
import com.amstech.balloon.decoration.system.repo.UserRepo;
import com.amstech.balloon.decoration.system.repo.UserRoleRepo;

@Service
public class UserRoleService {


	private final Logger LOGGER = LoggerFactory.getLogger(UserRoleService.class);

	@Autowired
	private UserRoleRepo userRoleRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private UserRoleEntityToModalConverter userRoleEntityToModalConverter;

	public UserRoleService() {
		LOGGER.debug("UserRoleService : Object Created");
	}

	public int assigneRole(UserRoleAssigneRequestModal userRoleAssigneRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(userRoleAssigneRequestModal.getUserId());
		if (!userOptional.isPresent()) {
			throw new Exception("User is not Available With id :  " + userRoleAssigneRequestModal.getUserId());
		}
		Optional<Role> roleOptional = roleRepo.findById(userRoleAssigneRequestModal.getRoleId());
		if (!roleOptional.isPresent()) {
			throw new Exception("Role is not Available With id :  " + userRoleAssigneRequestModal.getRoleId());
		}

		UserRole userRoleExist = userRoleRepo.findByUserIdRoleId(userOptional.get().getId(),
				roleOptional.get().getId());
		if (userRoleExist != null) {
			throw new Exception("That user Already Assigne  with role : " + roleOptional.get().getName());
		}

		UserRole userRole = new UserRole();
		userRole.setRole(roleOptional.get());
		userRole.setUser(userOptional.get());
		UserRole saveUserRole = userRoleRepo.save(userRole);
		return saveUserRole.getId();
		
	}
	
	public List<UserRoleResponseModal> findByRoleId(Integer roleId) throws Exception{
		Optional<Role> roleOptional = roleRepo.findById(roleId);
		if (!roleOptional.isPresent()) {
			throw new Exception("Role is not Available With id :  " + roleId);
		}
		
		List<UserRole> userRoles = userRoleRepo.findByRoleId(roleId);
		
		List<UserRoleResponseModal> userRoleResponseModals = userRoleEntityToModalConverter.findUserByRole(userRoles);
		
		return userRoleResponseModals;
	}
	
	public List<UserRoleResponseModal> findByUserId(Integer userId) throws Exception{
		Optional<User> userOptional = userRepo.findById(userId);
		if (!userOptional.isPresent()) {
			throw new Exception("User is not Available With id :  " + userId);
		}
		
		List<UserRole> userRoles = userRoleRepo.findByUserId(userId);
		
		List<UserRoleResponseModal> userRoleResponseModals = userRoleEntityToModalConverter.findUserByRole(userRoles);
		
		return userRoleResponseModals;
	}

}
