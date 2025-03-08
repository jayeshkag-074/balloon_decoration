package com.amstech.balloon.decoration.system.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amstech.balloon.decoration.system.converter.modal.BookingEntityToModalConverter;
import com.amstech.balloon.decoration.system.entity.Booking;
import com.amstech.balloon.decoration.system.entity.Decoration;
import com.amstech.balloon.decoration.system.entity.Status;
import com.amstech.balloon.decoration.system.entity.User;
import com.amstech.balloon.decoration.system.modal.request.BookingCreateRequestModal;
import com.amstech.balloon.decoration.system.modal.request.BookingUpdateRequestModal;
import com.amstech.balloon.decoration.system.modal.response.BookingReaponseModal;
import com.amstech.balloon.decoration.system.repo.BookingRepo;
import com.amstech.balloon.decoration.system.repo.DecorationRepo;
import com.amstech.balloon.decoration.system.repo.StatusRepo;
import com.amstech.balloon.decoration.system.repo.UserRepo;

@Service
public class BookingService {

	private final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private DecorationRepo decortionRepo;
	@Autowired
	private StatusRepo statusRepo;
	@Autowired
	private BookingRepo bookingRepo;
	@Autowired
	private BookingEntityToModalConverter bookingEntityToModalConverter;

	private int requestSentId = 11;

	public BookingService() {
		LOGGER.debug("BookingService : Object Created");
	}

	public int create(BookingCreateRequestModal bookingCreateRequestModal) throws Exception {
		Optional<User> userOptional = userRepo.findById(bookingCreateRequestModal.getUserId());
		if (!userOptional.isPresent()) {
			throw new Exception("User Is no Available with id  : " + bookingCreateRequestModal.getUserId());
		}
		Optional<Decoration> decorationOptional = decortionRepo.findById(bookingCreateRequestModal.getDecorationId());
		if (!decorationOptional.isPresent()) {
			throw new Exception("Decoration Is no Available with id  : " + bookingCreateRequestModal.getDecorationId());
		}
		Optional<Status> statusOptional = statusRepo.findById(requestSentId);
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + requestSentId);
		}

		List<Booking> bookingExits = bookingRepo.findByUserIdDecorationId(userOptional.get().getId(),decorationOptional.get().getId());
		if (bookingExits != null) {
			for (Booking bookingExit : bookingExits) {
				if (bookingExit.getStatus().getId() == requestSentId)
					throw new Exception("Booking Is Already Exist and which is Request Sent Status with id : "
							+ bookingExit.getId());
			}

		}

		Booking booking = new Booking();
		booking.setUser(userOptional.get());
		booking.setDecoration(decorationOptional.get());
		booking.setStatus(statusOptional.get());
		booking.setBookAt(new Date());
		booking.setUpdatedAt(new Date());

		Booking savedBooking = bookingRepo.save(booking);
		return savedBooking.getId();
	}

	public int updateStatus(BookingUpdateRequestModal bookingUpdateRequestModal) throws Exception {
		Optional<Booking> bookingOptional = bookingRepo.findById(bookingUpdateRequestModal.getId());
		if (!bookingOptional.isPresent()) {
			throw new Exception("Booking Is no Available with id  : " + bookingUpdateRequestModal.getId());
		}
		Optional<Status> statusOptional = statusRepo.findById(bookingUpdateRequestModal.getStatusId());
		if (!statusOptional.isPresent()) {
			throw new Exception("Status Is no Available with id  : " + bookingUpdateRequestModal.getStatusId());
		}

		if (bookingOptional.get().getStatus().getId() == statusOptional.get().getId()) {
			throw new Exception("Booking Is Already in " + statusOptional.get().getName() + " Status.");
		}
		Booking booking = bookingOptional.get();
		booking.setStatus(statusOptional.get());
		booking.setUpdatedAt(new Date());

		Booking updatedBooking = bookingRepo.save(booking);
		return updatedBooking.getStatus().getId();
	}

	public BookingReaponseModal findById(Integer id) throws Exception {
		Optional<Booking> bookingOptional = bookingRepo.findById(id);

		if (!bookingOptional.isPresent()) {
			throw new Exception("Booking Is no Available with id  : " + id);
		}
		Booking booking = bookingOptional.get();
		BookingReaponseModal bookingReaponseModal = bookingEntityToModalConverter.findById(booking);
		return bookingReaponseModal;
	}

	public List<BookingReaponseModal> findByUserId(Integer userId) throws Exception {

		Optional<User> userOptional = userRepo.findById(userId);
		if (!userOptional.isPresent()) {
			throw new Exception("Seneder Is no Available with id  : " + userId);
		}

		List<Booking> bookings = bookingRepo.findByUserId(userId);

		if (bookings.isEmpty()) {
			throw new Exception("No bookings Available with userId : " + userId);
		}

		List<BookingReaponseModal> bookingReaponseModals = bookingEntityToModalConverter.findByUserId(bookings);
		return bookingReaponseModals;

	}
}
