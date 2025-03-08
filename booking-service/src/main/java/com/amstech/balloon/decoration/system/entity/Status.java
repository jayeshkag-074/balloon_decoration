package com.amstech.balloon.decoration.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String description;

	private String name;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="status")
	private List<Booking> bookings;

	//bi-directional many-to-one association to Decorater
	@OneToMany(mappedBy="status")
	private List<Decorater> decoraters;

	//bi-directional many-to-one association to Decoration
	@OneToMany(mappedBy="status")
	private List<Decoration> decorations;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="status")
	private List<Notification> notifications;

	public Status() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setStatus(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setStatus(null);

		return booking;
	}

	public List<Decorater> getDecoraters() {
		return this.decoraters;
	}

	public void setDecoraters(List<Decorater> decoraters) {
		this.decoraters = decoraters;
	}

	public Decorater addDecorater(Decorater decorater) {
		getDecoraters().add(decorater);
		decorater.setStatus(this);

		return decorater;
	}

	public Decorater removeDecorater(Decorater decorater) {
		getDecoraters().remove(decorater);
		decorater.setStatus(null);

		return decorater;
	}

	public List<Decoration> getDecorations() {
		return this.decorations;
	}

	public void setDecorations(List<Decoration> decorations) {
		this.decorations = decorations;
	}

	public Decoration addDecoration(Decoration decoration) {
		getDecorations().add(decoration);
		decoration.setStatus(this);

		return decoration;
	}

	public Decoration removeDecoration(Decoration decoration) {
		getDecorations().remove(decoration);
		decoration.setStatus(null);

		return decoration;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setStatus(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setStatus(null);

		return notification;
	}

}