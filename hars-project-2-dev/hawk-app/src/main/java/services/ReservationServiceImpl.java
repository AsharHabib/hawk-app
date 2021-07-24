package services;

import java.util.List;

import dao.ReservationDao;
import dao.ReservationDaoImpl;
import models.Reservation;

public class ReservationServiceImpl implements ReservationService {
	ReservationDao reservationDao;
	
	public ReservationServiceImpl() {
		this.reservationDao = new ReservationDaoImpl();
	}

	@Override
	public List<Reservation> getAllReservations(int userId) {
		return this.reservationDao.getAllReservations(userId);
	}

	@Override
	public Reservation getReservation(int reservationId) {
		return this.reservationDao.getReservation(reservationId);
	}

	@Override
	public void createReservation(int userId, String json, String names) {
		this.reservationDao.createReservation(userId, json, names);
	}

	@Override
	public void deleteReservation(int reservationId) {
		this.reservationDao.deleteReservation(reservationId);
	}

}
