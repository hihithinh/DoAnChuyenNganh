package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.Video;
import bsh.uit.core.sql.MySQL;

public class VideoDAO {
	private MySQL mysql;
	private UserDAO userDao;
	private TypeDAO typeDao;
	
	public VideoDAO() {
		mysql = new MySQL();
		userDao = new UserDAO();
		typeDao = new TypeDAO();
	}
	
	public Video SQLtoVideo(ResultSet resultSet) throws Exception {
		Video video = new Video();
		while (resultSet.next()){
			video.setCreated_day(null);
			video.setDescription(resultSet.getString("description"));
			video.setId(resultSet.getString("video_id"));
			video.setInstrument(typeDao.getTypebyId(resultSet.getString("instrument")));
			video.setLink(resultSet.getString("link"));
			video.setStatus(resultSet.getInt("status"));
			video.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
		}
		return video;
	}
	
	public Video getVideobyId(String id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from video where video_id=?");
	        params.add(id);
			

	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoVideo(resultSet);
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
