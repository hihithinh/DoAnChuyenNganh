package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
			submit.setCreated_day(null);
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
}
