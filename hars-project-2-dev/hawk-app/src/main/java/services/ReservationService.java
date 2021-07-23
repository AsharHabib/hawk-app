package services;

import java.util.List;
import models.Reservation;

public interface ReservationService {
	List<Reservation> getAllReservations(int userId);
	Reservation getReservation(int reservationId);
	void createReservation(int userId, String json);
	void deleteReservation(int reservationId);
}
