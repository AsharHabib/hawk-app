package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconfig.ConnectionUtil;
import dbconfig.ResourceClosers;
import models.Traveller;

public class TravellersDaoImpl implements TravellersDao {

	@Override
	public List<Traveller> getAllSeats(int reservationId) {
		List<Traveller> seats = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			final String SQL = "select * from travellers where traveller_reservation_id = ?";
			stmt = conn.prepareStatement(SQL);
			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setInt(1, reservationId);
			set = stmt.executeQuery();
			while(set.next()) {
				Traveller seat = new Traveller(set.getInt(1), set.getInt(2), set.getString(3), set.getString(5), set.getString(6), set.getString(7), set.getInt(8));
				seats.add(seat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
			ResourceClosers.closeResultSet(set);
		}
		return seats;
	}

	@Override
	public void bookSeat(int reservationId, String planeSeat, String cabin, String flightDuration, String carrierCode, int flightNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtil.getConnection();
			final String SQL = "insert into travellers values(default, ?, ?, '', ?, ?, ?, ?)";
			stmt = conn.prepareStatement(SQL);
			
			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setInt(1, reservationId);
			stmt.setString(2, planeSeat);
			stmt.setString(3, cabin);
			stmt.setString(4, flightDuration);
			stmt.setString(5, carrierCode);
			stmt.setInt(6, flightNumber);
			
			//And of course, execute the SQL statement once you have set your parameters.
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
	}

	@Override
	public void updateSeat(int travellerId, String planeSeat) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionUtil.getConnection();
			final String SQL = "update travellers set traveller_plane_seat = ? where traveller_id = ?";
			stmt = conn.prepareStatement(SQL);
			
			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setString(1, planeSeat);
			stmt.setInt(2, travellerId);
			
			//And of course, execute the SQL statement once you have set your parameters.
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
	}

}
