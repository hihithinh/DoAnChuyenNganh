package bsh.uit.core.dao;

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
	
	public Project_Detail SQLtoDetail(ResultSet resultSet) throws Exception {
		Project_Detail detail = new Project_Detail();
		while (resultSet.next()){
			detail.setOrder(resultSet.getInt("order"));
			detail.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			detail.setVideo(videoDao.getVideobyId(resultSet.getString("video_id")));
			detail.setVideo_type(resultSet.getInt("video_type"));
			detail.setVolume(resultSet.getInt("volume"));
		}
		return detail;
	}
	
	public Project_Detail getDetailbyId(String project_id, String video_id) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from project_detail where project_id=?");
	        params.add(project_id);
	        sql.append(" and video_id=?");
	        params.add(video_id);
			return SQLtoDetail(mysql.doSQLQuery(sql.toString(), params));
		} catch (Exception e) {
            throw e;
		}
	}
}
