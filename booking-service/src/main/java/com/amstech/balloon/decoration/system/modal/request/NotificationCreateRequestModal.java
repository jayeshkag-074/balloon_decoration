package com.amstech.balloon.decoration.system.modal.request;

import lombok.Data;

@Data
public class NotificationCreateRequestModal {

	private int senderId;
	private int receiverId;
	private int decorationId;
	private String title;
	private String message;
}
