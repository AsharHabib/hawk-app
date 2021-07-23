package services;

import models.User;

import java.util.Map;

public interface UserService {
	Map<Integer, User> getAllUsers();
	void registerUser(String firstName, String lastName, String email, String password);
	User userLogIn(String email, String password);
}
