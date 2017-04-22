package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.Rating;
import bsh.uit.core.sql.MySQL;

public class RatingDAO {
	private MySQL mysql;
	private ProjectDAO projectDao;
	private UserDAO userDao;
	
	public RatingDAO() {
		mysql = new MySQL();
		projectDao = new ProjectDAO();
		userDao = new UserDAO();
	}
	
	public List<Rating> SQLtoRating(ResultSet resultSet) throws Exception {
		List<Rating> lstRating = new ArrayList<Rating>();
		while (resultSet.next()){
			Rating rating = new Rating();
			rating.setCreated_day(resultSet.getTimestamp("created_day"));
			rating.setDescription(resultSet.getString("description"));
			rating.setId(resultSet.getString("notify_id"));
			rating.setParent(getRatingbyId(resultSet.getString("parent_id")));
			rating.setPoint(resultSet.getInt("rating_point"));
			rating.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			rating.setType(resultSet.getInt("rating_type"));
			rating.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
			lstRating.add(rating);
		}
		return lstRating;
	}
	
	public Rating getRatingbyId(String id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from rating where rating_id=?");
	        params.add(id);
			
	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			
			//Chi lay gia tri dau tien
			return SQLtoRating(resultSet).get(0);
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
	
	public Rating getLastRating() throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from rating "
					+ "order by rating_id desc "
					+ "limit 1");
			
		  //execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());

			resultSet = preparedStatement.executeQuery();
			
			return SQLtoRating(resultSet).get(0);
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

	public String addRating(Rating rating) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("insert into rating "
					+ "(`RATING_ID`, `USER_ID`, `PROJECT_ID`, `RATING_POINT`, `DESCRIPTION`, `PARENT_ID`, `RATING_TYPE`, "
					+ "`CREATED_DAY`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			//Get last ID then +1
			Rating vid = new Rating();
			vid = getLastRating();
			String lastId = "R" + String.valueOf(Integer.parseInt(vid.getId().substring(1)) + 1);
			
			params.add(lastId);
			params.add(rating.getUser().getId());
			params.add(rating.getProject().getId());
			params.add(rating.getPoint());
			params.add(rating.getDescription());
			params.add(rating.getParent());
			params.add(rating.getType());
			params.add(rating.getCreated_day());

			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
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
	
	public String updateRating(Rating rating) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("update rating set "
					+ "`USER_ID`=?, `PROJECT_ID`=?, `RATING_POINT`=?, `DESCRIPTION`=?, `PARENT_ID`=?, "
					+ "`RATING_TYPE`=?, `CREATED_DAY`=? WHERE `RATING_ID`=?");

			params.add(rating.getUser().getId());
			params.add(rating.getProject().getId());
			params.add(rating.getPoint());
			params.add(rating.getDescription());
			params.add(rating.getParent());
			params.add(rating.getType());
			params.add(rating.getCreated_day());
			params.add(rating.getId());
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
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
	
	public String deleteRating(String id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("delete from rating where `RATING_ID`=?");
			params.add(id);
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
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
