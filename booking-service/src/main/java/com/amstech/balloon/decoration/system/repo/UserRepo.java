package com.amstech.balloon.decoration.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.balloon.decoration.system.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.email= :email")
	User findByemail(@Param("email") String email);
	
	@Query("select u from User u where u.mobileNumber= :mobileNumber")
	User findByMobilenumber(@Param("mobileNumber") String phoneNumber);
}
