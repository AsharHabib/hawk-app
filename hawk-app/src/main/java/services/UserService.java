package services;

import models.User;

import java.util.Map;

public interface UserService {
	String getOne();
	Map<Integer, User> getAllUsers();
	Map<Integer, User> checkUser(User user);
	void createUser(User user);
	void registerUser(String firstName, String lastName, String email, String password);
	Map<Integer, User> userLogIn(String email, String password);
}
