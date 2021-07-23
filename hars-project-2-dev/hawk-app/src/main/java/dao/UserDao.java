package dao;

import models.User;

import java.util.Map;

public interface UserDao {
	Map<Integer, User> getAllUsers();
	void registerUser(String firstName, String lastName, String email, String password);
	User userLogin(String email, String password);
}
