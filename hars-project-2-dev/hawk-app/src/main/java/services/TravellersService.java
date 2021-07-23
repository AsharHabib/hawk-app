package services;

import java.util.List;
import models.Traveller;

public interface TravellersService {
	List<Traveller> getAllSeats(int reservationId);
	void bookSeat(int reservationId, String planeSeat, String cabin, String flightDuration, String carrierCode, int flightNumber);
	void updateSeat(int travellerId, String planeSeat);
}