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
			rating.setCreated_day(null);
			rating.setDescription(resultSet.getString("description"));
			rating.setId(resultSet.getString("notify_id"));
			rating.setParent(getNotifybyId(resultSet.getString("parent_id")));
			rating.setPoint(resultSet.getInt("rating_point"));
			rating.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			rating.setType(resultSet.getInt("rating_type"));
			rating.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
			lstRating.add(rating);
		}
		return lstRating;
	}
	
	public Rating getNotifybyId(String id) throws Exception {
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
}
