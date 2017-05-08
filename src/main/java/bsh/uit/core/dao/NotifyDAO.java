package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
			notify.setCreated_day(resultSet.getTimestamp("created_day"));
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
			
			System.out.println(preparedStatement.toString());
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
	
	public List<Notify> getNotifybyProjectId(String pid) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from notify where project_id=?");
	        params.add(pid);
			
	      //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			//Chi tra ve gia tri dau tien trong List
			return SQLtoNotify(resultSet);
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
	
	public Notify getLastNotify() throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from notify "
					+ "order by notify_id desc "
					+ "limit 1");
			
		  //execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());

			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
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

	public Notify addNotify(Notify notify) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("insert into notify "
					+ "(`NOTIFY_ID`, `USER_ID`, `DESCRIPTION`, `PROJECT_ID`, `STATUS`, `CREATED_DAY`) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
			
			//Get last ID then +1
			Notify vid = new Notify();
			vid = getLastNotify();
			String lastId = "N" + String.valueOf(Integer.parseInt(vid.getId().substring(1)) + 1);
			
			params.add(lastId);
			params.add(notify.getUser().getId());
			params.add(notify.getDescription());
			params.add(notify.getProject().getId());
			params.add(notify.getStatus());
			params.add(new Date());

			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			
			if(Success == 0)
				return null;
			return getNotifybyId(lastId);
		} catch (Exception e) {
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
	
	public Notify updateNotify(Notify notify) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("update notify SET "
					+ "`USER_ID`=?, `DESCRIPTION`=?, `PROJECT_ID`=?, `STATUS`=?, `CREATED_DAY`=? "
					+ "WHERE `NOTIFY_ID`=?");

			params.add(notify.getUser().getId());
			params.add(notify.getDescription());
			params.add(notify.getProject().getId());
			params.add(notify.getStatus());
			params.add(notify.getCreated_day());
			params.add(notify.getId());
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			if(Success == 0)
				return null;
			return getNotifybyId(notify.getId());
		} catch (Exception e) {
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
	
	public String deleteNotify(String id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("delete from notify where `NOTIFY_ID`=?");
			params.add(id);
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			if(Success == 0)
				return "error";
			return "success";
		} catch (Exception e) {
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
}
