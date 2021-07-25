package travellersTest;

import com.amadeus.Travel;
import dao.ReservationDaoImpl;
import dao.TravellersDaoImpl;
import dbconfig.ResourceClosers;
import models.Reservation;
import models.Traveller;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Using a local test database with the same structure as the production database
// Then mock the connection and results
public class TravellersDaoTest {
    @Mock
    Connection connection;
    @Mock
    ResultSet resultSet;
    @Mock
    PreparedStatement prepStmt;
    @InjectMocks
    public TravellersDaoImpl travellersDaoImpl;
    @BeforeClass
    public void setUp(){
        travellersDaoImpl = new TravellersDaoImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSeats(){
        List<Traveller> expectedTravellers = new ArrayList<>();
        List<Traveller> actualTravellers;
        int testReservationId = 5;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","kent123q");
            final String SQL = "select * from travellers where traveller_reservation_id = ?";
            prepStmt = connection.prepareStatement(SQL);
            //Since the SQL statement is parameterized, I need to set the values of
            //the parameters.
            prepStmt.setInt(1, testReservationId);
            resultSet = prepStmt.executeQuery();
            while(resultSet.next()) {
                Traveller seat = new Traveller(resultSet.getInt("traveller_id"), resultSet.getInt("traveller_reservation_id"),
                        resultSet.getString("traveller_plane_seat"), resultSet.getString("traveller_luggage"),
                        resultSet.getString("traveller_cabin"), resultSet.getString("traveller_estimated_flight_duration"),
                        resultSet.getInt("carrier_code"));
                expectedTravellers.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResourceClosers.closeConnection(connection);
            ResourceClosers.closeStatement(prepStmt);
            ResourceClosers.closeResultSet(resultSet);
        }
        actualTravellers = travellersDaoImpl.getAllSeats(testReservationId);
        int index = 0;
        for(Traveller getSeats: actualTravellers){
            Assert.assertEquals(getSeats.getPlaneSeat(),expectedTravellers.get(index).getPlaneSeat());
            index++;
        }
        System.out.println("This is a Test Traveller DAO - Get All Seats");
    }
    @Test
    public void testBookSeat(){
        System.out.println("This is a Test Traveller DAO - Booking a seat");
    }
    @Test
    public void testUpdateSeat(){
        System.out.println("This is a Test Traveller DAO - Updating a seat");
    }
}
