package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconfig.ConnectionUtil;
import dbconfig.ResourceClosers;
import models.Reservation;

public class ReservationDaoImpl implements ReservationDao {

	@Override
	public List<Reservation> getAllReservations(int userId) {
		List<Reservation> reservations = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionUtil.getConnection();

			//Now that we've gotten a connection, we want to execute our SQL
			//statement. In order to execute the SQL statement, we will need to use
			//the Statement interface.
			final String SQL = "select * from reservations where reservation_user_id = ?";
			stmt = conn.prepareStatement(SQL);
			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setInt(1, userId);
			set = stmt.executeQuery();
			while (set.next()) {
				Reservation reservation = new Reservation(set.getInt(1), set.getInt(2), set.getString(3), set.getString(4));
				reservations.add(reservation);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
			ResourceClosers.closeResultSet(set);
		}
		return reservations;
	}

	@Override
	public Reservation getReservation(int reservationId) {
		Reservation reservation = new Reservation();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		
		try {
			conn = ConnectionUtil.getConnection();

			//Now that we've gotten a connection, we want to execute our SQL
			//statement. In order to execute the SQL statement, we will need to use
			//the Statement interface.
			final String SQL = "select * from reservations where reservation_id = ?";
			stmt = conn.prepareStatement(SQL);
			
			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setInt(1, reservationId);
			set = stmt.executeQuery();
			
			while(set.next()) {
				reservation.setId(set.getInt(1));
				reservation.setUserId(set.getInt(2));
				reservation.setReservationJSON(set.getString(3));
				reservation.setNamesJSON(set.getString(4));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
			ResourceClosers.closeResultSet(set);
		}
		return reservation;
	}

	@Override
	public void createReservation(int userId, String json, String names) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			final String SQL = "insert into reservations values(default, ?, ?, ?)";
			stmt = conn.prepareStatement(SQL);
			
			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setInt(1, userId);
			stmt.setString(2, json);
			stmt.setString(3, names);
			
			//And of course, execute the SQL statement once you have set your parameters.
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
	}

	@Override
	public void deleteReservation(int reservationId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			final String SQL = "delete from reservations where reservation_id = ?";
			stmt = conn.prepareStatement(SQL);
			
			//Since the SQL statement is parameterized, I need to set the values of
			//the parameters.
			stmt.setInt(1, reservationId);
			
			//And of course, execute the SQL statement once you have set your parameters.
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ResourceClosers.closeConnection(conn);
			ResourceClosers.closeStatement(stmt);
		}
	}

}
