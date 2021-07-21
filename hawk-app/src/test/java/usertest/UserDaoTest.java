package usertest;

import dao.UserDao;
import dao.UserDaoImpl;

import models.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

// Using a local test database with the same structure as the production database
public class UserDaoTest {
    @Mock
    Connection connection;
    @Mock
    ResultSet resultSet;
    @Mock
    Statement statement;
    @InjectMocks
    public UserDaoImpl userDaoImpl;


//    HashMap<Integer,User> mockData;
//    HashMap<Integer, User> mockUsers;

    @BeforeClass
    public void setUp() throws SQLException {
        userDaoImpl = new UserDaoImpl();
        MockitoAnnotations.openMocks(this);
        User user1 = new User(1,"Honey", "Bunney","hblakers.com","nice123");
//        mockData = new HashMap<>();
//        mockData = mockData.put(1, mockUsers);
//        mockData =  mockData.put(1, new User(1, "Honey", "Bunny",
//                "hb@lakers.com","yessir"));
//        mockData.put(2, new User(2, "James", "Harden",
//                "jamesharden@lakers.com","threep"));
//        mockData.put(3, new User(3, "Dwayne", "Wade",
//                "dream@lakers.com","miami"));
//        mockData.put(4, new User(4, "Rob", "Can",
//                "rdc@lakers.com","colony"));
//        mockData.put(5, new User(5, "Wong", "Shun",
//                "corona@lakers.com","virus123"));

    }
    @Test
    public void testGetAllUsers(){
        Map<Integer, User> users = new HashMap<>();
        Map<Integer, User> expectedUsers = new HashMap<>();
        Map<Integer, User> actualUsers = new HashMap<>();

//        Connection conn;
//        Statement stmt;
//        ResultSet set;

        try{
            // This is the local data base url, username and password for testing
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","kent123q");
            final String SQL = "select * from users";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            System.out.println(resultSet);
            while(resultSet.next()) {
                User retrievedUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5));
                users.put(resultSet.getInt(1), retrievedUser);
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        expectedUsers = users;
        actualUsers = userDaoImpl.getAllUsers();
        System.out.println(expectedUsers);

        Assert.assertEquals(actualUsers,expectedUsers);

        System.out.println("This is for getting all the users in the database");
    }
    @Test
    public void registerUser(){
        System.out.println("This is for registering the user");
    }
    @Test
    public void userLogin(){
        System.out.println("This is for user login");
    }
}
