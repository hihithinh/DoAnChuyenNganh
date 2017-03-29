package bsh.uit.core.dao;

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
	
	public Submit_Queue SQLtoSubmit(ResultSet resultSet) throws Exception {
		Submit_Queue submit = new Submit_Queue();
		while (resultSet.next()){
			submit.setCreated_day(null);
			submit.setDescription(resultSet.getString("description"));
			submit.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			submit.setVideo(videoDao.getVideobyId(resultSet.getString("video_id")));
			submit.setVolume(resultSet.getInt("volume"));
		}
		return submit;
	}
	
	public Submit_Queue getSubmitbyId(String project_id, String video_id) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from submit_queue where project_id=?");
	        params.add(project_id);
	        sql.append(" and video_id=?");
	        params.add(video_id);
			return SQLtoSubmit(mysql.doSQLQuery(sql.toString(), params));
		} catch (Exception e) {
            throw e;
		}
	}
}
