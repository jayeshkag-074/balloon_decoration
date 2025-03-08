package com.amstech.balloon.decoration.system.request.Modal;

import lombok.Data;

@Data
public class UserSignUpRequestModal {

	
	private int locationId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String gender;
	private String password;
	
}
