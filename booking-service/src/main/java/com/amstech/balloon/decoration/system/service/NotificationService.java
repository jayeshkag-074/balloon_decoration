package com.amstech.balloon.decoration.system.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.balloon.decoration.system.converter.modal.NotificationEntityToModalConverter;
import com.amstech.balloon.decoration.system.entity.Decoration;
import com.amstech.balloon.decoration.system.entity.Notification;
import com.amstech.balloon.decoration.system.entity.Status;
import com.amstech.balloon.decoration.system.entity.User;
import com.amstech.balloon.decoration.system.modal.request.NotificationCreateRequestModal;
import com.amstech.balloon.decoration.system.modal.response.NotificationResponseModal;
import com.amstech.balloon.decoration.system.repo.DecorationRepo;
import com.amstech.balloon.decoration.system.repo.NotificationRepo;
import com.amstech.balloon.decoration.system.repo.StatusRepo;
import com.amstech.balloon.decoration.system.repo.UserRepo;

@Service
public class NotificationService {

	private final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private DecorationRepo decorationRepo ;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	private NotificationEntityToModalConverter notificationEntityToModalConverter;
	
	private int unReadId = 1;
	private int readId = 2;
	
	public NotificationService() {
		LOGGER.debug("NotificationService : Object Created");
	}
	
	public int create(NotificationCreateRequestModal notificationCreateRequestModal) throws Exception {

		Optional<User> senderOptional = userRepo.findById(notificationCreateRequestModal.getSenderId());
		if (!senderOptional.isPresent()) {
			throw new Exception("Seneder Is no Available with id  : " + notificationCreateRequestModal.getSenderId());
		}
		
		Optional<User> receiverOptional = userRepo.findById(notificationCreateRequestModal.getReceiverId());
		if (!receiverOptional.isPresent()) {
			throw new Exception("Receiver Is no Available with id  : " + notificationCreateRequestModal.getReceiverId());
		}

		Optional<Decoration> decorationOptional = decorationRepo.findById(notificationCreateRequestModal.getDecorationId());
		if (!decorationOptional.isPresent()) {
			throw new Exception("Decoration Is no Available with id  : " + notificationCreateRequestModal.getDecorationId());
		}

		
		
		Optional<Status> statusOptional = statusRepo.findById(unReadId);
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + unReadId);
		}
		
		List<Notification> notificationExists = notificationRepo.findBySenderIdReciver(senderOptional.get().getId(), receiverOptional.get().getId());
		if (notificationExists != null) {
			for (Notification notificationExist : notificationExists) {
				if (notificationExist.getStatus().getId() == unReadId)
					throw new Exception(
							"Notifcation Is Already Send and which is unreaded with id : " + notificationExist.getId());
			}

		}
		
		Notification notification = new Notification();
		notification.setUser2(senderOptional.get()); 
		notification.setUser1(receiverOptional.get());
		notification.setDecoration(decorationOptional.get());
		notification.setTitle(notificationCreateRequestModal.getTitle());
		notification.setMessage(notificationCreateRequestModal.getMessage());
		notification.setStatus(statusOptional.get());
		notification.setCreatedAt(new Date());
		Notification savedNotification = notificationRepo.save(notification);
		return savedNotification.getId();

	}

	public void markRead(Integer id) throws Exception {
		Optional<Notification> notificationOptional = notificationRepo.findById(id);
		if (!notificationOptional.isPresent()) {
			throw new Exception("Notification Is not Available with id  : " + id);
		}

		
		Optional<Status> statusOptional = statusRepo.findById(readId);
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is not Available with id  : " + readId);
		}

		if (notificationOptional.get().getStatus().getId() == readId) {
			throw new Exception("Notification Is Already readed  with id : " + id);
		}
		Notification notification = notificationOptional.get();
		notification.setStatus(statusOptional.get());
		notificationRepo.save(notification);

	}

	public List<NotificationResponseModal> findAllForReciver(Integer receiverId) throws Exception {
		Optional<User> receiverOptional = userRepo.findById(receiverId);
		if (!receiverOptional.isPresent()) {
			throw new Exception("User Is no Available with id  : " + receiverId);
		}

		
		List<Notification> notifications = notificationRepo.findAllByReceiverId(receiverId);
		
		if (notifications.isEmpty()) {
			throw new Exception("Not Notification Exist for user with id : " + receiverId);
		}
		

		List<NotificationResponseModal> notificationResponseModals = notificationEntityToModalConverter.findAllByReceiverId(notifications);
		return notificationResponseModals;

	}
	
}
