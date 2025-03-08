package com.amstech.balloon.decoration.system.converter.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.amstech.balloon.decoration.system.entity.Booking;
import com.amstech.balloon.decoration.system.modal.response.BookingReaponseModal;

@Component
public class BookingEntityToModalConverter {

	public BookingReaponseModal findById(Booking booking) {
		BookingReaponseModal bookingReaponseModal = new BookingReaponseModal();
		bookingReaponseModal.setId(booking.getId());
		bookingReaponseModal.setDecorationName(booking.getDecoration().getName());
		bookingReaponseModal.setDecoratorName(booking.getDecoration().getDecorater().getUser().getFirstName());
		bookingReaponseModal.setDescorationPrice(booking.getDecoration().getPrice());
		bookingReaponseModal.setDecorationDescription(booking.getDecoration().getDescription());
		bookingReaponseModal.setStatusName(booking.getStatus().getName());
		return bookingReaponseModal;
	}
	
	public List<BookingReaponseModal> findByUserId(List<Booking> bookings) {

		List<BookingReaponseModal> bookingReaponseModals = new ArrayList<>();
		for (Booking booking : bookings) {
			BookingReaponseModal bookingReaponseModal = new BookingReaponseModal();
			bookingReaponseModal.setId(booking.getId());
			bookingReaponseModal.setDecorationName(booking.getDecoration().getName());
			bookingReaponseModal.setDecoratorName(booking.getDecoration().getDecorater().getUser().getFirstName());
			bookingReaponseModal.setDescorationPrice(booking.getDecoration().getPrice());
			bookingReaponseModal.setDecorationDescription(booking.getDecoration().getDescription());
			bookingReaponseModal.setStatusName(booking.getStatus().getName());

			bookingReaponseModals.add(bookingReaponseModal);

		}
		return bookingReaponseModals;
	}
}
