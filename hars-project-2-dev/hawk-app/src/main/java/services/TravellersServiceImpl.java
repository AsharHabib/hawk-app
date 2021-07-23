package services;

import java.util.List;
import dao.TravellersDao;
import dao.TravellersDaoImpl;
import models.Traveller;

public class TravellersServiceImpl implements TravellersService {
	TravellersDao travellersDao;
	
	

	public TravellersServiceImpl() {
		this.travellersDao = new TravellersDaoImpl();
	}

	@Override
	public List<Traveller> getAllSeats(int reservationId) {
		return this.travellersDao.getAllSeats(reservationId);
	}

	@Override
	public void bookSeat(int reservationId, String planeSeat, String cabin, String flightDuration, String carrierCode, int flightNumber) {
		this.travellersDao.bookSeat(reservationId, planeSeat, cabin, flightDuration, carrierCode, flightNumber);
	}

	@Override
	public void updateSeat(int travellerId, String planeSeat) {
		this.travellersDao.updateSeat(travellerId, planeSeat);
	}

}
