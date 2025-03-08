package com.amstech.balloon.decoration.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.balloon.decoration.system.entity.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

	@Query("select n from Notification n where n.user2.id =:senderId and n.user1.id =:receiverId")
	List<Notification> findBySenderIdReciver(@Param("senderId") int senderId, @Param("receiverId") int receiverId);
	
	@Query("select n from Notification n where n.user1.id =:receiverId and n.status.id =1")
	List<Notification> findAllByReceiverId( @Param("receiverId") int receiverId);
}
