package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.Project_Detail;
import bsh.uit.core.sql.MySQL;

public class DetailDAO {
	private MySQL mysql;
	private ProjectDAO projectDao;
	private VideoDAO videoDao;
	
	public DetailDAO() {
		mysql = new MySQL();
		projectDao = new ProjectDAO();
		videoDao = new VideoDAO();
	}
	
	public List<Project_Detail> SQLtoDetail(ResultSet resultSet) throws Exception {
		List<Project_Detail> lstDetail = new ArrayList<Project_Detail>();
		while (resultSet.next()){
			Project_Detail detail = new Project_Detail();
			detail.setOrder(resultSet.getInt("order"));
			detail.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			detail.setVideo(videoDao.getVideobyId(resultSet.getString("video_id")));
			detail.setVideo_type(resultSet.getInt("video_type"));
			detail.setVolume(resultSet.getInt("video_volume"));
			lstDetail.add(detail);
		}
		return lstDetail;
	}
	
	public List<Project_Detail> getDetailbyId(String project_id, String video_id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from project_detail where project_id=?");
	        params.add(project_id);
	        if(video_id != null) {
		        sql.append(" and video_id=?");
		        params.add(video_id);
	        }
	      //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoDetail(resultSet);
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
