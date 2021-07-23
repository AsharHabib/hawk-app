package dao;

import models.Reservation;
import java.util.List;

public interface ReservationDao {
	List<Reservation> getAllReservations(int userId);
	Reservation getReservation(int reservationId);
	void createReservation(int userId, String json);
	void deleteReservation(int reservationId);
}
