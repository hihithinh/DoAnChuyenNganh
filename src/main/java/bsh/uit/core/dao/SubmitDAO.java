package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.uit.core.entities.Submit_Queue;
import bsh.uit.core.sql.MySQL;

public class SubmitDAO {
	private MySQL mysql;
	private ProjectDAO projectDao;
	private VideoDAO videoDao;
	
	public SubmitDAO() {
		mysql = new MySQL();
		projectDao = new ProjectDAO();
		videoDao = new VideoDAO();
	}
	
	public List<Submit_Queue> SQLtoSubmit(ResultSet resultSet) throws Exception {
		List<Submit_Queue> lstSubmit = new ArrayList<Submit_Queue>();
		while (resultSet.next()){
			Submit_Queue submit = new Submit_Queue();
			submit.setCreated_day(resultSet.getTimestamp("created_day"));
			submit.setDescription(resultSet.getString("description"));
			submit.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			submit.setVideo(videoDao.getVideobyId(resultSet.getString("video_id")));
			submit.setVolume(resultSet.getInt("volume"));
			lstSubmit.add(submit);
		}
		return lstSubmit;
	}
	
	public List<Submit_Queue> getSubmitbyId(String project_id, String video_id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from submit_queue where project_id=?");
	        params.add(project_id);
	        sql.append(" and video_id=?");
	        params.add(video_id);

	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoSubmit(resultSet);
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
	
	public Submit_Queue addSubmit_Queue(Submit_Queue submit) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("insert into submit_queue "
					+ "(`PROJECT_ID`, `VIDEO_ID`, `DESCRIPTION`, `VIDEO_VOLUME`, `CREATED_DAY`, `STATUS`) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
			
			params.add(submit.getProject().getId());
			params.add(submit.getVideo().getId());
			params.add(submit.getDescription());
			params.add(submit.getVolume());
			params.add(new Date());
			params.add(submit.getStatus());
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			
			if(Success == 0)
				return null;
			return getSubmitbyId(submit.getProject().getId(), submit.getVideo().getId()).get(0);
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
	
	public Submit_Queue updateSubmit_Queue(Submit_Queue submit) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("update submit_queue set "
					+ "`DESCRIPTION`=?, `VIDEO_VOLUME`=?, `CREATED_DAY`=?, `STATUS`=? "
					+ "WHERE `PROJECT_ID`=? and`VIDEO_ID`=?");

			params.add(submit.getDescription());
			params.add(submit.getVolume());
			params.add(submit.getCreated_day());
			params.add(submit.getStatus());
			params.add(submit.getProject().getId());
			params.add(submit.getVideo().getId());
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			if(Success == 0)
				return null;
			return getSubmitbyId(submit.getProject().getId(), submit.getVideo().getId()).get(0);
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
	
	public String deleteSubmitbyProjectId(String pid) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("delete from submit where `PROJECT_ID`=?");
			params.add(pid);
			
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
	
	public String deleteSubmitbyVideoId(String vid) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("delete from submit where `VIDEO_ID`=?");
			params.add(vid);
			
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
