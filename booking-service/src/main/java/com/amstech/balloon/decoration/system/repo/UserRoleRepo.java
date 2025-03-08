package com.amstech.balloon.decoration.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.balloon.decoration.system.entity.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer>{

	@Query("select ur from UserRole ur where ur.user.id =:userId and ur.role.id =:roleId")
	UserRole findByUserIdRoleId( @Param("userId") int userId,@Param("roleId") int roleId);
	
	@Query("select ur from UserRole ur where ur.role.id =:roleId")
	List<UserRole> findByRoleId(@Param("roleId") int roleId);
	
	@Query("select ur from UserRole ur where ur.user.id =:userId")
	List<UserRole> findByUserId( @Param("userId") int userId);
	
	
}
