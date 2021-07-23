package models;

public class Reservation {
	private int id;
	private int userId;
	private String reservationJSON;
	
	public Reservation(int id, int userId, String reservationJSON) {
		super();
		this.id = id;
		this.userId = userId;
		this.reservationJSON = reservationJSON;
	}
	public Reservation() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getReservationJSON() {
		return reservationJSON;
	}
	public void setReservationJSON(String reservationJSON) {
		this.reservationJSON = reservationJSON;
	}
	
}
