package com.amstech.balloon.decoration.system.converter.modal;

import java.util.ArrayList;
import java.util.List;

import com.amstech.balloon.decoration.system.entity.Notification;
import com.amstech.balloon.decoration.system.modal.response.NotificationResponseModal;

public class NotificationEntityToModalConverter {

	public List<NotificationResponseModal> findAllByReceiverId(List<Notification> notifications){
		List<NotificationResponseModal> notificationResponseModals = new ArrayList<>();
		for (Notification notification : notifications) {
			NotificationResponseModal notificationResponseModal = new NotificationResponseModal();
			notificationResponseModal.setId(notification.getId());
			notificationResponseModal.setReceiverId(notification.getUser1().getId());
			notificationResponseModal.setReceiverName(notification.getUser1().getFirstName());
			notificationResponseModal.setSenderId(notification.getUser2().getId());
			notificationResponseModal.setSenderName(notification.getUser2().getFirstName());
			notificationResponseModal.setTripId(notification.getDecoration().getId());
			notificationResponseModal.setTripName(notification.getDecoration().getName());
			notificationResponseModal.setTitle(notification.getTitle());
			notificationResponseModal.setMessage(notification.getMessage());
			notificationResponseModal.setStatusName(notification.getStatus().getName());
			notificationResponseModal.setCreatedAt(notification.getCreatedAt());
			
			notificationResponseModals.add(notificationResponseModal);
		}
		return notificationResponseModals;
	}
}
