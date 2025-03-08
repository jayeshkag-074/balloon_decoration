package com.amstech.balloon.decoration.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.balloon.decoration.system.entity.Booking;

public interface BookingRepo  extends JpaRepository<Booking, Integer>{

	@Query("select b from Booking b where b.user.id =:userId and b.decoration.id =:decorationId")
	List<Booking> findByUserIdDecorationId(@Param("userId") int userId, @Param("decorationId") int decorationId);
	
	@Query("select b from Booking b where b.user.id =:userId")
	List<Booking> findByUserId(@Param("userId") int userId);
}
