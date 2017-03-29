package bsh.uit.core.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.Notify;
import bsh.uit.core.sql.MySQL;

public class NotifyDAO {
	private MySQL mysql;
	private ProjectDAO projectDao;
	private UserDAO userDao;
	
	public NotifyDAO() {
		mysql = new MySQL();
		projectDao = new ProjectDAO();
		userDao = new UserDAO();
	}
	
	public Notify SQLtoNotify(ResultSet resultSet) throws Exception {
		Notify notify = new Notify();
		while (resultSet.next()){
			notify.setCreated_day(null);
			notify.setDescription(resultSet.getString("description"));
			notify.setId(resultSet.getString("notify_id"));
			notify.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			notify.setStatus(resultSet.getInt("status"));
			notify.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
		}
		return notify;
	}
	
	public Notify getNotifybyId(String id) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from notify where notify_id=?");
	        params.add(id);
			
			return SQLtoNotify(mysql.doSQLQuery(sql.toString(), params));
		} catch (Exception e) {
            throw e;
		}
	}
}
