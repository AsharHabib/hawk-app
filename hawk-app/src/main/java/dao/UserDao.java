package dao;

import models.User;

import java.util.Map;

public interface UserDao {
	String getOne();
	Map<Integer, User> getAllUsers();
	Map<Integer, User> checkUser();
	void createUser(User user);
}
