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
	public Map<Integer, User> checkUser() {
		return this.userDao.checkUser();
	}

	@Override
	public void createUser(User user) {
		this.userDao.createUser(user);
	}


}
