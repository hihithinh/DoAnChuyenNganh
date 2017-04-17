package bsh.uit.core.mgr;

import java.sql.SQLException;

import bsh.uit.core.dao.UserDAO;
import bsh.uit.core.entities.User;

public class UserMgr {
	UserDAO userDao;
	
	public UserMgr() throws ClassNotFoundException, SQLException {
		userDao = new UserDAO();
	}
	
	public User loginUser(String account, String password) throws Exception {
		return userDao.loginUser(account, password);
	}
	
	public User getUserbyId(String id) throws Exception {
		return userDao.getUserbyId(id);
	}
	
	public User addUser(User user) throws Exception {
		return userDao.addUser(user);
	}
	
	public User updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}
	
	public String deleteUser(String id, String password) throws Exception {
		return userDao.deleteUser(id, password);
	}
}
