package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
	
	public List<Video> SQLtoVideo(ResultSet resultSet) throws Exception {
		List<Video> lstVideo = new ArrayList<Video>();
		while (resultSet.next()){
			Video video = new Video();
			video.setCreated_day(resultSet.getTimestamp("created_day"));
			video.setDescription(resultSet.getString("description"));
			video.setId(resultSet.getString("video_id"));
			video.setInstrument(typeDao.getTypebyId(resultSet.getString("instrument")));
			video.setLink(resultSet.getString("link"));
			video.setStatus(resultSet.getInt("status"));
			video.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
			lstVideo.add(video);
		}
		return lstVideo;
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
			
			return SQLtoVideo(resultSet).get(0);
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
	
	public List<Video> getVideobyUserId(String uid) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from video where user_id=?");
	        params.add(uid);
			

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
	
	public Video getLastVideo() throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			
    		StringBuilder sql = new StringBuilder();
	        sql.append("select * from video "
	        		+ "order by created_day desc "
	        		+ "limit 1");
	        
	      //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());

			resultSet = preparedStatement.executeQuery();
			
			return SQLtoVideo(resultSet).get(0);
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

	public Video addVideo(Video video) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
	    	StringBuilder sql = new StringBuilder();
	    	List<Object> params = new ArrayList<Object>();
	        sql.append("insert into video "
	        		+ "(`VIDEO_ID`, `USER_ID`, `LINK`, `INSTRUMENT`, `DESCRIPTION`, `STATUS`, `CREATED_DAY`) "
	        		+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
	        
	        //Get last ID then +1
	        Video vid = new Video();
	        vid = getLastVideo();
	        String lastId = "V" + String.valueOf(Integer.parseInt(vid.getId().substring(1)) + 1);
	        
	        params.add(lastId);
	        params.add(video.getUser().getId());
	        params.add(video.getLink());
	        params.add(video.getInstrument().getId());
	        params.add(video.getDescription());
	        params.add(video.getStatus());
	        params.add(new Date());
	         
	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			Success = preparedStatement.executeUpdate();
			
			if(Success == 0)
				return null;
			return getVideobyId(lastId);
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
    
    public Video updateVideo(Video video) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
	    	StringBuilder sql = new StringBuilder();
	    	List<Object> params = new ArrayList<Object>();
	        sql.append("update video set "
	        		+ "`USER_ID`=?, `LINK`=?, `INSTRUMENT`=?, `DESCRIPTION`=?, `CREATED_DAY`=? "
	        		+ "WHERE `VIDEO_ID`=?");

	        params.add(video.getUser().getId());
	        params.add(video.getLink());
	        params.add(video.getInstrument().getId());
	        params.add(video.getDescription());
	        params.add(video.getStatus());
	        params.add(video.getCreated_day());
	        params.add(video.getId());
	        
	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			Success = preparedStatement.executeUpdate();
			if(Success == 0)
				return null;
			return getVideobyId(video.getId());
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
    
    public String deleteVideo(String id) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
	    	StringBuilder sql = new StringBuilder();
	    	List<Object> params = new ArrayList<Object>();
	        sql.append("delete from video where `VIDEO_ID`=?");
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
