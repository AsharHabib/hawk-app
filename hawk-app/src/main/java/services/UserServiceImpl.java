package services;

import dao.UserDao;
import dao.UserDaoImpl;

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
	
}
