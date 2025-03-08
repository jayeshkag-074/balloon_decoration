package com.amstech.balloon.decoration.system.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.balloon.decoration.system.entity.UserRole;
import com.amstech.balloon.decoration.system.modal.response.UserRoleResponseModal;

@Component
public class UserRoleEntityToModalConverter {

	public List<UserRoleResponseModal> findUserByRole(List<UserRole> userRoles){
		List<UserRoleResponseModal> userRoleResponseModals = new ArrayList<>();
		for (UserRole userRole : userRoles) {
			UserRoleResponseModal userRoleResponseModal = new UserRoleResponseModal();
			userRoleResponseModal.setId(userRole.getId());
			userRoleResponseModal.setRoleName(userRole.getRole().getName());
			userRoleResponseModal.setUserName(userRole.getUser().getFirstName());
			
			userRoleResponseModals.add(userRoleResponseModal);
		}
		return userRoleResponseModals;
	}
}
