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
		try {
			User user = userDao.loginUser(account, password);
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
}
