package services;

import dao.UserDao;
import dao.UserDaoImpl;
import models.User;

import java.util.Map;

public class UserServiceImpl implements UserService {
	UserDao userDao;
	
	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
	}
	
	@Override
	public String getOne() {
		// TODO Auto-generated method stub
		this.userDao.getOne();
		return "Working";
	}

	@Override
	public Map<Integer, User> getAllUsers() {
		return this.userDao.getAllUsers();
	}

	@Override
	public void createUser(User user) {
		this.userDao.createUser(user);
	}

	public Map<Integer, User> checkUser(User user) {
		return this.userDao.checkUser(user);
	}

	@Override
	public void registerUser(String firstName, String lastName, String email, String password){
		this.userDao.registerUser(firstName, lastName, email, password);
	}

	@Override
	public Map<Integer, User> userLogIn(String email, String password) {
		return this.userDao.userLogin(email, password);
	}


}
