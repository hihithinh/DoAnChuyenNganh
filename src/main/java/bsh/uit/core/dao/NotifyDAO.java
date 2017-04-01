package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public List<Notify> SQLtoNotify(ResultSet resultSet) throws Exception {
		List<Notify> lstNotify = new ArrayList<Notify>();
		while (resultSet.next()){
			Notify notify = new Notify();
			notify.setCreated_day(null);
			notify.setDescription(resultSet.getString("description"));
			notify.setId(resultSet.getString("notify_id"));
			notify.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			notify.setStatus(resultSet.getInt("status"));
			notify.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
			lstNotify.add(notify);
		}
		return lstNotify;
	}
	
	public Notify getNotifybyId(String id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from notify where notify_id=?");
	        params.add(id);
			
	      //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			
			//Chi tra ve gia tri dau tien trong List
			return SQLtoNotify(resultSet).get(0);
    	} catch (Exception e) {
            throw e;
	    } finally {
			if(resultSet != null) {
				resultSet.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
}
